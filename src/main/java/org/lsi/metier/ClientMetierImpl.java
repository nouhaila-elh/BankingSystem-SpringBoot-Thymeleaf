package org.lsi.metier;

import java.util.List;

import org.lsi.dao.ClientRepository;
import org.lsi.entities.Client;
import org.lsi.entities.Compte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ClientMetierImpl implements ClientMetier {
	
	
	@Autowired
	private ClientRepository clientRepository;

	@Override
	public Client saveClient(Client c) {
		// TODO Auto-generated method stub
		return clientRepository.save(c);
	}

	@Override
	public Page<Client> listClient(int page,int size) {
		// TODO Auto-generated method stub
		return clientRepository.findAll(PageRequest.of(page, size));
	}

}
