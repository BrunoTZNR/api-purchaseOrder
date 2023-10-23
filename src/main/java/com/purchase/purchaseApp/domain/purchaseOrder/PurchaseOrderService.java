package com.purchase.purchaseApp.domain.purchaseOrder;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.purchase.purchaseApp.domain.client.Client;
import com.purchase.purchaseApp.domain.client.ClientService;

@Service
public class PurchaseOrderService {

	@Autowired
	private PurchaseOrderRepository purchaseOrderRepository;
	
	@Autowired
	private ClientService clientService;
	
	//create ordem de compra
	public Object createPurchaseOrder(PurchaseOrderRecord purchaseOrderRecord) {
		Optional<Client> client = clientService.getClient(purchaseOrderRecord.clientCpf());
		
		if(client.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado!");
		}
	
		var purchaseOrder0 = new PurchaseOrder();
		
		purchaseOrder0.setDataPedido(purchaseOrderRecord.dataPedido());
		purchaseOrder0.setClient(client.get());
		
		return purchaseOrderRepository.save(purchaseOrder0);
	}
	
	//pegar todas as ordens
	public List<PurchaseOrder> getAllPurchaseOrders() {
		return purchaseOrderRepository.findAll();
	}
	
	//pegar uma ordem
	public Optional<PurchaseOrder> getPurchaseOrder(Integer numPedido) {
		Optional<PurchaseOrder> purchaseOrder0 = purchaseOrderRepository.findById(numPedido);
		
		if(purchaseOrder0.isEmpty()) {
			return null;
		}
		
		return purchaseOrder0;
	}
	
	//editar uma ordem
	public Object editPurchaseOrder(Integer numPedido, PurchaseOrderRecord purchaseOrderRecord) {
		Optional<PurchaseOrder> purchaseOrder0 = purchaseOrderRepository.findById(numPedido);
		
		if(purchaseOrder0.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ordem de pedido não encontrado!");
		}
		
		Optional<Client> client = clientService.getClient(purchaseOrderRecord.clientCpf());
		
		if(client.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado!");
		}
		
		var purchaseOrder = purchaseOrder0.get();
		
		purchaseOrder.setDataPedido(purchaseOrderRecord.dataPedido());
		purchaseOrder.setClient(client.get());
		
		return purchaseOrderRepository.save(purchaseOrder);
	}
	
	//deletar uma ordem
	public Object deletePurchaseOrder(Integer numPedido) {
		Optional<PurchaseOrder> purchaseOrder0 = purchaseOrderRepository.findById(numPedido);
		
		if(purchaseOrder0.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ordem de pedido não encontrado!");
		}
		
		purchaseOrderRepository.deleteById(numPedido);
		
		return ResponseEntity.status(HttpStatus.OK).body("Ordem de pedido deletado!");
	}
}
