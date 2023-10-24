package com.purchase.purchaseApp.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.purchase.purchaseApp.domain.client.Client;
import com.purchase.purchaseApp.domain.client.ClientRecord;
import com.purchase.purchaseApp.domain.client.ClientService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("v1/client")
public class ClientController {

	@Autowired
	private ClientService clientService;
	
	@PostMapping
	public ResponseEntity<Client> createClient(@RequestBody @Valid ClientRecord clientRecord) {
		return ResponseEntity.status(HttpStatus.CREATED).body(clientService.createClient(clientRecord));
	}
	
	@GetMapping
	public ResponseEntity<List<Client>> getAllClients() {
		return ResponseEntity.status(HttpStatus.OK).body(clientService.getAllClients());
	}
	
	@GetMapping("/{cpf}")
	public ResponseEntity<Object> getClient(@PathVariable(value = "cpf") String cpf) {
		Optional<Client> client0 = clientService.getClient(cpf);
		
		if(client0.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado!");
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(clientService.getClient(cpf));
	}
	
	@PutMapping("/{cpf}")
	public ResponseEntity<Object> editClient(@PathVariable(value = "cpf") String cpf,
			@RequestBody @Valid ClientRecord clientRecord) {
		Optional<Client> client0 = clientService.getClient(cpf);
		
		if(client0.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado!");
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(clientService.editClient(cpf, clientRecord));
	}
	
	@DeleteMapping("/{cpf}")
	public ResponseEntity<Object> deleteClient(@PathVariable(value = "cpf") String cpf) {
		Optional<Client> client0 = clientService.getClient(cpf);
		
		if(client0.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado!");
		}
		
		clientService.deleteClient(cpf);
		
		return ResponseEntity.status(HttpStatus.OK).body("Cliente deletado!");
	}
}
