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
import com.purchase.purchaseApp.domain.client.ClientService;
import com.purchase.purchaseApp.domain.purchaseOrder.PurchaseOrder;
import com.purchase.purchaseApp.domain.purchaseOrder.PurchaseOrderRecord;
import com.purchase.purchaseApp.domain.purchaseOrder.PurchaseOrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("v1/purchaseOrder")
public class PurchaseOrderController {
	
	@Autowired
	private PurchaseOrderService purchaseOrderService;
	
	@Autowired
	private ClientService clientService;
	
	@PostMapping
	public ResponseEntity<Object> createPurchaseOrder(@RequestBody @Valid PurchaseOrderRecord purchaseOrderRecord) {
		Optional<Client> client = clientService.getClient(purchaseOrderRecord.clientCpf());
		
		if(client.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado!");
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(purchaseOrderService.createPurchaseOrder(purchaseOrderRecord));
	}
	
	@GetMapping
	public ResponseEntity<List<PurchaseOrder>> getAllPurchaseOrders() {
		return ResponseEntity.status(HttpStatus.OK).body(purchaseOrderService.getAllPurchaseOrders());
	}
	
	@GetMapping("/{numPedido}")
	public ResponseEntity<Object> getPurchaseOrder(@PathVariable(value = "numPedido") Integer numPedido) {
		return ResponseEntity.status(HttpStatus.OK).body(purchaseOrderService.getPurchaseOrder(numPedido));
	}
	
	@PutMapping("/{numPedido}")
	public ResponseEntity<Object> editPurchaseOrder(@PathVariable(value = "numPedido") Integer numPedido, 
			@RequestBody @Valid PurchaseOrderRecord purchaseOrderRecord) {
		Optional<PurchaseOrder> purchaseOrder0 = purchaseOrderService.getPurchaseOrder(numPedido);
		Optional<Client> client = clientService.getClient(purchaseOrderRecord.clientCpf());
		
		if(purchaseOrder0.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ordem de pedido não encontrado!");
		} else if(client.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado!");
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(purchaseOrderService.editPurchaseOrder(numPedido, purchaseOrderRecord));
	}
	
	@DeleteMapping("/{numPedido}")
	public ResponseEntity<Object> editPurchaseOrder(@PathVariable(value = "numPedido") Integer numPedido) {
		Optional<PurchaseOrder> purchaseOrder0 = purchaseOrderService.getPurchaseOrder(numPedido);
		
		if(purchaseOrder0.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ordem de pedido não encontrado!");
		}
		
		purchaseOrderService.deletePurchaseOrder(numPedido);
		
		return ResponseEntity.status(HttpStatus.OK).body("Pedido deletado com sucesso!");
	}
}
