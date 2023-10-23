package com.purchase.purchaseApp.controllers;

import java.util.List;

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

import com.purchase.purchaseApp.domain.purchaseOrder.PurchaseOrder;
import com.purchase.purchaseApp.domain.purchaseOrder.PurchaseOrderRecord;
import com.purchase.purchaseApp.domain.purchaseOrder.PurchaseOrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("v1/purchaseOrder")
public class PurchaseOrderController {
	
	@Autowired
	private PurchaseOrderService purchaseOrderService;
	
	@PostMapping
	public ResponseEntity<Object> createPurchaseOrder(@RequestBody @Valid PurchaseOrderRecord purchaseOrderRecord) {
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
		return ResponseEntity.status(HttpStatus.OK).body(purchaseOrderService.editPurchaseOrder(numPedido, purchaseOrderRecord));
	}
	
	@DeleteMapping("/{numPedido}")
	public ResponseEntity<Object> editPurchaseOrder(@PathVariable(value = "numPedido") Integer numPedido) {
		return ResponseEntity.status(HttpStatus.OK).body(purchaseOrderService.deletePurchaseOrder(numPedido));
	}
}
