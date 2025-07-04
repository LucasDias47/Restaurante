package com.example.demo.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.PratoModel;

public interface PratoRepository extends JpaRepository<PratoModel, UUID>{

}
