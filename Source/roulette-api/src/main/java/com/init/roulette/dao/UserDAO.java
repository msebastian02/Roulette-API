package com.init.roulette.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.init.roulette.entitys.User;

public interface UserDAO extends JpaRepository<User, Integer>{

}
