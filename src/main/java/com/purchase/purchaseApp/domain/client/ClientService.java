package com.purchase.purchaseApp.domain.client;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.purchase.purchaseApp.domain.productItem.ProductItem;
import com.purchase.purchaseApp.domain.purchaseOrder.PurchaseOrder;
import com.purchase.purchaseApp.domain.purchaseOrder.PurchaseOrderService;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private PurchaseOrderService purchaseOrderService;
	
	//cadastrar cliente
	public Client createClient(ClientRecord clientRecord) {
		var client0 = new Client();
		
		BeanUtils.copyProperties(clientRecord, client0);
		
		return clientRepository.save(client0);
	}
	
	//pegar todos os clientes
	public List<Client> getAllClients() {
		return clientRepository.findAll();
	}
	
	//pegar um cliente
	public Optional<Client> getClient(String cpf) {
		return clientRepository.findById(cpf);
	}
	
	//editar um cliente
	public Client editClient(String cpf, ClientRecord clientRecord) {
		Optional<Client> client0 = clientRepository.findById(cpf);
		
		var client = client0.get();
		
		BeanUtils.copyProperties(clientRecord, client);
		
		return clientRepository.save(client);
	}
	
	//deletar cliente
	public void deleteClient(String cpf) {
		List<PurchaseOrder> purchaseOrder0 = purchaseOrderService.getAllPurchaseOrders();
		
		for(PurchaseOrder po : purchaseOrder0) {
			if(po.getClient().getCpf() == cpf) {
				if(!po.getProductItems().isEmpty()) {
					for(ProductItem pi : po.getProductItems()) {
						pi.getProduct().controlQuantity(pi.getQuantity(), "sum");
					}
				}
			}
		}
		
		clientRepository.deleteById(cpf);
	}
}
