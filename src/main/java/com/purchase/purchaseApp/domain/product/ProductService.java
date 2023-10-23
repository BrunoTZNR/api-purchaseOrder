package com.purchase.purchaseApp.domain.product;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	//criar produto
	public Product createProduct(ProductRecord productRecord) {
		var product = new Product();
		
		BeanUtils.copyProperties(productRecord, product);
		
		return productRepository.save(product);
	}
	
	//pegar todos os produtos
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}
	
	//pegar produto por id
	public Optional<Product> getProduct(Integer id) {
		return productRepository.findById(id);
	}
	
	//editar um produto
	public Product editProduct(Integer id, ProductRecord productRecord) {
		Optional<Product> product0 = productRepository.findById(id);
		
		var prod = product0.get();
		
		BeanUtils.copyProperties(productRecord, prod);
		
		return productRepository.save(prod);
	}
	
	//deletar um produto
	public void deleteProduct(Integer id) {
		productRepository.deleteById(id);
	}
}
