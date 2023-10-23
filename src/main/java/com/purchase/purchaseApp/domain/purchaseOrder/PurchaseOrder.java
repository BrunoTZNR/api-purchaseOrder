package com.purchase.purchaseApp.domain.purchaseOrder;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import com.purchase.purchaseApp.domain.client.Client;
import com.purchase.purchaseApp.domain.productItem.ProductItem;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_purchaseOrder")
public class PurchaseOrder implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "numPedido_purchaseOrder")
	private Integer numPedido;
	
	@Column(name = "dtPedido_purchaseOrder", nullable = false)
	private LocalDate dataPedido;
	
	@ManyToOne
	@JoinColumn(name = "fk_client", nullable = false)
	private Client client;
	
	@OneToMany(mappedBy = "purchaseOrder", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<ProductItem> productItems;

	public Integer getNumPedido() {
		return numPedido;
	}

	public LocalDate getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(LocalDate dataPedido) {
		this.dataPedido = dataPedido;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<ProductItem> getProductItems() {
		return productItems;
	}

	public void setProductItems(List<ProductItem> productItems) {
		this.productItems = productItems;
	}
}
