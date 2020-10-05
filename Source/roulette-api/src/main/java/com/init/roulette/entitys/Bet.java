package com.init.roulette.entitys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bets")
public class Bet {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "userBet")
	private String userBet;
	@Column(name = "status", nullable = true)
	private String status;
	@Column(name = "betAmount")
	private int betAmount;
	@Column(name = "userId")	
	private int userId;
	@Column(name = "rouletteId")	
	private int rouletteId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserBet() {
		return userBet;
	}
	public void setUserBet(String userBet) {
		this.userBet = userBet;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getBetAmount() {
		return betAmount;
	}
	public void setBetAmount(int betAmount) {
		this.betAmount = betAmount;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getRouletteId() {
		return rouletteId;
	}
	public void setRouletteId(int rouletteId) {
		this.rouletteId = rouletteId;
	}
}
