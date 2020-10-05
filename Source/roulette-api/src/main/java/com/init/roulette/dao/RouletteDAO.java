package com.init.roulette.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.init.roulette.entitys.Roulette;

public interface RouletteDAO extends JpaRepository<Roulette, Integer>{

}
