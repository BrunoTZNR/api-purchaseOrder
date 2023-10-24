package com.purchase.purchaseApp.domain.purchaseOrder;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.purchase.purchaseApp.domain.client.ClientService;
import com.purchase.purchaseApp.domain.product.ProductService;
import com.purchase.purchaseApp.domain.productItem.ProductItem;

@Service
public class PurchaseOrderService {

	@Autowired
	private PurchaseOrderRepository purchaseOrderRepository;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private ProductService productService;
	
	//create ordem de compra
	public PurchaseOrder createPurchaseOrder(PurchaseOrderRecord purchaseOrderRecord) {
		var client = clientService.getClient(purchaseOrderRecord.clientCpf());

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
		return purchaseOrderRepository.findById(numPedido);
	}
	
	//editar uma ordem
	public PurchaseOrder editPurchaseOrder(Integer numPedido, PurchaseOrderRecord purchaseOrderRecord) {
		var purchaseOrder0 = purchaseOrderRepository.findById(numPedido);
		var client = clientService.getClient(purchaseOrderRecord.clientCpf());

		var purchaseOrder = purchaseOrder0.get();
		
		purchaseOrder.setDataPedido(purchaseOrderRecord.dataPedido());
		purchaseOrder.setClient(client.get());
		
		return purchaseOrderRepository.save(purchaseOrder);
	}
	
	//deletar uma ordem
	public void deletePurchaseOrder(Integer numPedido) {
		var purchaseOrder0 = purchaseOrderRepository.findById(numPedido);
		
		for(ProductItem pi : purchaseOrder0.get().getProductItems()) {
			pi.getProduct().controlQuantity(pi.getQuantity(), "sum");
			
			productService.saveProduct(pi.getProduct());
		}
		
		purchaseOrderRepository.deleteById(numPedido);
	}
}
