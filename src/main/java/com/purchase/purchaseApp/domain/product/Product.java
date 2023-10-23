package com.purchase.purchaseApp.domain.product;

import java.io.Serializable;
import java.util.List;

import com.purchase.purchaseApp.domain.productItem.ProductItem;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_product")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Product implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_product")
	private Integer id;
	
	@Column(name = "name_product", nullable = false, length = 50)
	private String name;
	
	@Column(name = "description_product", nullable = false, length = 50)
	private String description;
	
	@Column(name = "quantity_product", nullable = false)
	private Integer quantity;
	
	@Column(name = "amount_product", nullable = false)
	private Double amount;
	
	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
	private List<ProductItem> productItems;

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	/*public List<ProductItem> getProductItems() {
		return productItems;
	}*/

	public void setProductItems(List<ProductItem> productItems) {
		this.productItems = productItems;
	}
	
	//diminui a quantidade de itens
	public void controlQuantity(Integer subQuantity) {
		this.quantity = this.quantity - subQuantity;
	}
}
