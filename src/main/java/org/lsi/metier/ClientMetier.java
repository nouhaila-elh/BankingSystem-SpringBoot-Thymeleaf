package org.lsi.metier;

import java.util.List;

import org.lsi.entities.Client;
import org.lsi.entities.Compte;
import org.springframework.data.domain.Page;

public interface ClientMetier {

	public Client saveClient(Client c);
	public Page<Client> listClient(int page,int size);
	
}


