package com.purchase.purchaseApp.domain.client;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;
	
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
		clientRepository.deleteById(cpf);
	}
}
