package com.purchase.purchaseApp.domain.productItem;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.purchase.purchaseApp.domain.product.Product;
import com.purchase.purchaseApp.domain.product.ProductRepository;
import com.purchase.purchaseApp.domain.product.ProductService;
import com.purchase.purchaseApp.domain.purchaseOrder.PurchaseOrder;
import com.purchase.purchaseApp.domain.purchaseOrder.PurchaseOrderService;

@Service
public class ProductItemService {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProductItemRepository productItemRepository;
	
	@Autowired
	private PurchaseOrderService purchaseOrderService;
	
	@Autowired
	private ProductService productService;
	
	//create produto item
	public Object createProductItem(ProductItemRecord productItemRecord) {
		Optional<Product> product0 = productService.getProduct(productItemRecord.productId());
		Optional<PurchaseOrder> purchaseOrder0 = purchaseOrderService.getPurchaseOrder(productItemRecord.purchaseOrderId());
		
		if(product0.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado!");
		} else if(purchaseOrder0.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lista de compra não encontrada!");
		}
		
		var productItem = new ProductItem();
		
		productItem.setQuantity(productItemRecord.quantity());
		productItem.setAmount(productItemRecord.amount());
		productItem.setProduct(product0.get());
		productItem.setPurchaseOrder(purchaseOrder0.get());
		
		product0.get().controlQuantity(productItemRecord.quantity());
		
		//product0.get().setQuantity(product0.get().getQuantity() - productItemRecord.quantity());
		
		//productService.editProduct(product0.get().getId(), product0.get());
		
		productRepository.save(product0.get());
		
		return productItemRepository.save(productItem);
	}
	
	//pegar todos os produtos do item
}
