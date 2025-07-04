package com.example.demo.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.ClienteModel;

public interface ClienteRepository extends JpaRepository<ClienteModel, UUID> {

}
