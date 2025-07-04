package com.example.demo.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Services.ClienteService;
import com.example.demo.dtos.ClienteRecordDto;
import com.example.demo.models.ClienteModel;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	private final ClienteService clienteService;

	public ClienteController(ClienteService clienteService) {
		
		this.clienteService = clienteService;
	}
	
	@PostMapping
	public ResponseEntity<ClienteModel>saveCliente(@RequestBody ClienteRecordDto  dto){
		return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.saveCliente(dto));
	}
	
    @GetMapping
    public ResponseEntity<List<ClienteModel>> getAllClientes() {
        return ResponseEntity.ok(clienteService.getAllClientes());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCliente(@PathVariable UUID id) {
        clienteService.deleteCliente(id);
        return ResponseEntity.ok("Cliente deletado com sucesso.");
    }
}