package org.lsi.metier;


import java.util.Date;
import java.util.List;

import org.lsi.dao.CompteRepository;
import org.lsi.entities.Compte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class CompteMetierImpl implements CompteMetier {
	
	
	@Autowired
	private CompteRepository compteRepository;

	@Override
	public Compte saveCompte(Compte cp) {
		// TODO Auto-generated method stub
		cp.setDateCreation(new Date());
		return compteRepository.save(cp);
	}

	@Override
	public Compte getCompte(Long code) {
		// TODO Auto-generated method stub
		return compteRepository.findById(code).orElse(null);
	}

	@Override
	public Page<Compte> findCompteByClient(String code,int page,int size) {
		return compteRepository.findCompteByClient(code,PageRequest.of(page, size));
	}

	

}
