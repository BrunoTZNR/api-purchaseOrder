package com.purchase.purchaseApp.domain.productItem;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.purchase.purchaseApp.domain.product.ProductService;
import com.purchase.purchaseApp.domain.purchaseOrder.PurchaseOrderService;

@Service
public class ProductItemService {

	@Autowired
	private ProductItemRepository productItemRepository;
	
	@Autowired
	private PurchaseOrderService purchaseOrderService;
	
	@Autowired
	private ProductService productService;
	
	//create produto item
	public ProductItem createProductItem(ProductItemRecord productItemRecord) {
		var product0 = productService.getProduct(productItemRecord.productId());
		var purchaseOrder0 = purchaseOrderService.getPurchaseOrder(productItemRecord.purchaseOrderId());
		
		var productItem = new ProductItem();
		
		productItem.setQuantity(productItemRecord.quantity());
		productItem.setAmount(productItemRecord.amount());
		productItem.setProduct(product0.get());
		productItem.setPurchaseOrder(purchaseOrder0.get());
		
		product0.get().controlQuantity(productItemRecord.quantity(), "sub");
		
		productService.saveProduct(product0.get());
		
		return productItemRepository.save(productItem);
	}
	
	//pegar todos os produtos do item
	public List<ProductItem> getAllProductItems() {
		return productItemRepository.findAll();
	}
	
	//pega um produto do item
	public Optional<ProductItem> getProductItem(Integer id) {
		return productItemRepository.findById(id);
	}
	
	//editar item do produto
	public ProductItem editProductItem(Integer id, ProductItemRecord productItemRecord) {
		var productItem0 = productItemRepository.findById(id);
		var product0 = productService.getProduct(productItemRecord.productId());
		var purchaseOrder0 = purchaseOrderService.getPurchaseOrder(productItemRecord.purchaseOrderId());
		
		var productItem = productItem0.get();
		
		if(productItem.getQuantity() != productItemRecord.quantity()) {
			if(productItem.getQuantity() > productItemRecord.quantity()) {
				productItem.getProduct().controlQuantity(productItem.getQuantity() - productItemRecord.quantity(), "sum");
			} else if(productItem.getQuantity() < productItemRecord.quantity()){
				productItem.getProduct().controlQuantity(productItemRecord.quantity() - productItem.getQuantity(), "sub");
			}
			
			productService.saveProduct(productItem.getProduct());
		}
		
		productItem.setAmount(productItemRecord.amount());
		productItem.setQuantity(productItemRecord.quantity());
		productItem.setProduct(product0.get());
		productItem.setPurchaseOrder(purchaseOrder0.get());
		
		return productItemRepository.save(productItem);
	}
	
	//deletar produto do item
	public void deleteProductItem(Integer id) {
		Optional<ProductItem> productItem0 = productItemRepository.findById(id);
		var product0 = productItem0.get().getProduct();
		
		product0.controlQuantity(productItem0.get().getQuantity(), "sum");
		
		productService.saveProduct(product0);
		
		productItemRepository.deleteById(id);
	}
}
