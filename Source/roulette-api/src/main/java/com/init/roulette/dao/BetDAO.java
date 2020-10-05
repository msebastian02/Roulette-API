package com.init.roulette.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.init.roulette.entitys.Bet;

public interface BetDAO extends JpaRepository<Bet, Integer>{
	public List<Bet> findByRouletteId(int rouletteId);
}
