package org.lsi.metier;

import java.util.List;

import org.lsi.entities.Compte;
import org.springframework.data.domain.Page;

public interface CompteMetier {
	
	public Compte saveCompte(Compte cp);
	public Compte getCompte(Long code);
	public Page<Compte> findCompteByClient(String code,int page,int size);

}
