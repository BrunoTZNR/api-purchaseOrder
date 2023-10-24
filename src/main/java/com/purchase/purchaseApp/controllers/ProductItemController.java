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

import com.purchase.purchaseApp.domain.product.Product;
import com.purchase.purchaseApp.domain.product.ProductService;
import com.purchase.purchaseApp.domain.productItem.ProductItem;
import com.purchase.purchaseApp.domain.productItem.ProductItemRecord;
import com.purchase.purchaseApp.domain.productItem.ProductItemService;
import com.purchase.purchaseApp.domain.purchaseOrder.PurchaseOrder;
import com.purchase.purchaseApp.domain.purchaseOrder.PurchaseOrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("v1/productItem")
public class ProductItemController {

	@Autowired
	private ProductItemService productItemService;
	
	@Autowired
	private PurchaseOrderService purchaseOrderService;
	
	@Autowired
	private ProductService productService;
	
	@PostMapping
	public ResponseEntity<Object> createProductItem(@RequestBody @Valid ProductItemRecord productItemRecord) {
		Optional<Product> product0 = productService.getProduct(productItemRecord.productId());
		Optional<PurchaseOrder> purchaseOrder0 = purchaseOrderService.getPurchaseOrder(productItemRecord.purchaseOrderId());
		
		if(product0.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado!");
		} else if(purchaseOrder0.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lista de compra não encontrada!");
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(productItemService.createProductItem(productItemRecord));
	}
	
	@GetMapping
	public ResponseEntity<List<ProductItem>> getAllProductItems() {
		return ResponseEntity.status(HttpStatus.OK).body(productItemService.getAllProductItems());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getProductItem(@PathVariable(value = "id") Integer id) {
		Optional<ProductItem> productItem0 = productItemService.getProductItem(id);
		
		if(productItem0.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item do produto não encontrado!");
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(productItem0);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> editProductItem(@PathVariable(value = "id") Integer id,
			@RequestBody @Valid ProductItemRecord productItemRecord) {
		Optional<ProductItem> productItem0 = productItemService.getProductItem(id);
		
		if(productItem0.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item do produto não encontrado!");
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(productItemService.editProductItem(id, productItemRecord));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteProductItem(@PathVariable(value = "id") Integer id) {
		Optional<ProductItem> productItem0 = productItemService.getProductItem(id);
		
		if(productItem0.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item do produto não encontrado!");
		}
		
		productItemService.deleteProductItem(id);
		
		return ResponseEntity.status(HttpStatus.OK).body("Item do produto deletado!");
	}
}
