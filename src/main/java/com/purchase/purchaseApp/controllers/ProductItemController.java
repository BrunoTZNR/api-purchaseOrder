package com.purchase.purchaseApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.purchase.purchaseApp.domain.productItem.ProductItemRecord;
import com.purchase.purchaseApp.domain.productItem.ProductItemService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("v1/productItem")
public class ProductItemController {

	@Autowired
	private ProductItemService productItemService;
	
	@PostMapping
	public ResponseEntity<Object> createProductItem(@RequestBody @Valid ProductItemRecord productItemRecord) {
		return ResponseEntity.status(HttpStatus.CREATED).body(productItemService.createProductItem(productItemRecord));
	}
}
