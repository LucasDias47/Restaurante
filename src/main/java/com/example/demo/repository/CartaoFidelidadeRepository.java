package com.example.demo.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.CartaoFidelidadeModel;

public interface CartaoFidelidadeRepository extends JpaRepository<CartaoFidelidadeModel,UUID>{
	

}
