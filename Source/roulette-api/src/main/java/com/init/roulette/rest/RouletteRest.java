package com.init.roulette.rest;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.init.roulette.dao.BetDAO;
import com.init.roulette.dao.RouletteDAO;
import com.init.roulette.dao.UserDAO;
import com.init.roulette.entitys.Bet;
import com.init.roulette.entitys.Roulette;
import com.init.roulette.entitys.User;

@RestController
@RequestMapping("/")
public class RouletteRest {
	
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private RouletteDAO rouletteDAO;
	@Autowired
	private BetDAO betDAO;
	
	@PostMapping
	@RequestMapping(value = "create", method = RequestMethod.POST)
	public ResponseEntity<Integer> createRoulette () {
		Roulette roulette = new Roulette();
		Roulette newRoulette = rouletteDAO.save(roulette);
		
		return ResponseEntity.ok(newRoulette.getId());
	} 
	
	@PostMapping
	@RequestMapping(value = "createuser", method = RequestMethod.POST)
	public ResponseEntity<User> createUser (@RequestBody User user) {
		User newUser = userDAO.save(user);
		
		return ResponseEntity.ok(newUser);
	} 
	
	@PutMapping
	@RequestMapping(value = "open", method = RequestMethod.PUT)
	public ResponseEntity<String> openRoulette (@RequestBody Roulette roulette) {
		Optional<Roulette> optionalRoulette = rouletteDAO.findById(roulette.getId());
		if(optionalRoulette.isPresent()) {
			Roulette updateRoulette = optionalRoulette.get();
			updateRoulette.setStatus(true);
			rouletteDAO.save(updateRoulette);
			
			return ResponseEntity.ok("Successful operation");
		}else {
			
			return ResponseEntity.badRequest().build();
		}
	} 
	
	@PostMapping
	@RequestMapping(value = "makebet", method = RequestMethod.POST)
	public ResponseEntity<Bet> makeBet (@RequestBody Bet bet, @RequestHeader("userId") int userId){
		if(validateBet(bet)) {
			bet.setUserId(userId);
			Bet newBet = betDAO.save(bet);
			
			return ResponseEntity.ok(newBet);
		}	
		
		return ResponseEntity.badRequest().build();
	} 
	
	@PutMapping
	@RequestMapping(value = "close", method = RequestMethod.PUT)
	public ResponseEntity<List<Bet>> closeRoulette (@RequestBody Roulette roulette) {
		Optional<Roulette> optionalRoulette = rouletteDAO.findById(roulette.getId());
		if(optionalRoulette.isPresent()) {
			Roulette updateRoulette = optionalRoulette.get();
			updateRoulette.setStatus(false);
			rouletteDAO.save(updateRoulette);
			
			return ResponseEntity.ok(rewards(roulette.getId()));
			
		}	
		
		return ResponseEntity.badRequest().build();
	} 
	
	@GetMapping
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public ResponseEntity<List<Roulette>> getRouletteList() {
		List<Roulette> roulette = rouletteDAO.findAll();
		
		return ResponseEntity.ok(roulette);
	}
	
	public boolean validateBet(Bet bet) {
		int idRoulette = bet.getRouletteId();
		int betAmount = bet.getBetAmount();
		String userBet = bet.getUserBet();
		Optional<Roulette> optionalRoulette = rouletteDAO.findById(idRoulette);
		if(!optionalRoulette.isPresent()) {
			
			return false;
		}
		Roulette roulette = optionalRoulette.get();
		if(roulette.isStatus()==false|(betAmount>10000)) {
			
			return false;
		}
		if((userBet.equalsIgnoreCase("red"))|(userBet.equalsIgnoreCase("black"))) {
			
			return true;
		}
		if((Integer.parseInt(userBet)>=0)&(Integer.parseInt(userBet)<=36)) {
			
			return true;
		}
		
		return false;
	}
	
	public List<Bet> rewards(int idRoulette) {
		List<Bet> bet = betDAO.findByRouletteId(idRoulette);
		String userBet;
		Random rand = new Random();
		int winNumber = rand.nextInt(36);
		for(Bet item:bet) {
			userBet = item.getUserBet();
			if(userBet.equalsIgnoreCase("red")|userBet.equalsIgnoreCase("black")) {
				if(parity(winNumber)&userBet.equalsIgnoreCase("red")) {
					item.setStatus("COLOR WIN");
					payments(item);
				} else if(!parity(winNumber)&userBet.equalsIgnoreCase("black")) {
					item.setStatus("COLOR WIN");
					payments(item);
				} else {
					item.setStatus("LOSE");
					payments(item);
				}
			} else if(Integer.parseInt(userBet) == winNumber) {
				item.setStatus("WIN");
				payments(item);
			} else {
				item.setStatus("LOSE");
				payments(item);
			}
		}
		
		return bet;
	}
	
	public void payments(Bet bet) {
		int betAmount = bet.getBetAmount();
		String status = bet.getStatus();
		User user = new User();
		Optional<User> optionalUser = userDAO.findById(bet.getUserId());
		user = optionalUser.get();
		if(status=="COLOR WIN") {
			user.setCredit((betAmount*1.8)+user.getCredit());
		} else if(status=="WIN") {
			user.setCredit((betAmount*5)+user.getCredit());
		} else {
			user.setCredit(user.getCredit()-betAmount);
		}
		userDAO.save(user);
	}
	
	public boolean parity(int number) {
		if (number%2!=0)
			return true;
		else
			return false;
	}
			
}
