package com.purchase.purchaseApp.domain.client;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import com.purchase.purchaseApp.domain.purchaseOrder.PurchaseOrder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_client")
public class Client implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	private String cpf;
	
	@Column(name = "name_client", nullable = false, length = 20)
	private String name;
	
	@Column(name = "email_client", nullable = false, length = 100)
	private String email;
	
	@Column(name = "dtNasc_client", nullable = false)
	private LocalDate dtNasc;
	
	@OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
	private List<PurchaseOrder> pedidos;

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDtNasc() {
		return dtNasc;
	}

	public void setDtNasc(LocalDate dtNasc) {
		this.dtNasc = dtNasc;
	}

	/*public List<PurchaseOrder> getPedidos() {
		return pedidos;
	}*/

	public void setPedidos(List<PurchaseOrder> pedidos) {
		this.pedidos = pedidos;
	}
}
