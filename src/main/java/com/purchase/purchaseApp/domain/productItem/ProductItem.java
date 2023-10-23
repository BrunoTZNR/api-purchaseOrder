package com.purchase.purchaseApp.domain.productItem;

import java.io.Serializable;

import com.purchase.purchaseApp.domain.product.Product;
import com.purchase.purchaseApp.domain.purchaseOrder.PurchaseOrder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_productItem")
public class ProductItem implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_productItem")
	private Integer id;
	
	@Column(name = "quantity_productItem", nullable = false)
	private Integer quantity;
	
	@Column(name = "amount_productItem", nullable = false)
	private Double amount;
	
	@ManyToOne
	@JoinColumn(name = "fk_product", nullable = false)
	private Product product;
	
	@ManyToOne
	@JoinColumn(name = "fk_purchaseOrder", nullable = false)
	private PurchaseOrder purchaseOrder;

	public Integer getId() {
		return id;
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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	/*public PurchaseOrder getPurchaseOrder() {
		return purchaseOrder;
	}*/

	public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}
}
