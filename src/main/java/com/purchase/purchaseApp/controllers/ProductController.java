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

import com.purchase.purchaseApp.domain.product.Product;
import com.purchase.purchaseApp.domain.product.ProductRecord;
import com.purchase.purchaseApp.domain.product.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("v1/product")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@PostMapping
	public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductRecord productRecord) {
		return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(productRecord));
	}
	
	@GetMapping
	public ResponseEntity<List<Product>> getAllProducts() {
		return ResponseEntity.status(HttpStatus.OK).body(productService.getAllProducts());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getProduct(@PathVariable(value = "id") Integer id) {
		var product0 = productService.getProduct(id);
		
		if(product0.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(product0);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> editProduct(@PathVariable(value = "id") Integer id, 
			@RequestBody @Valid ProductRecord productRecord) {
		var product0 = productService.getProduct(id);
		
		if(product0.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(productService.editProduct(id, productRecord));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteProduct(@PathVariable(value = "id") Integer id) {
		var product0 = productService.getProduct(id);
		
		if(product0.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		productService.deleteProduct(id);
		
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}
