package org.lsi.metier;


import org.lsi.dao.GroupeRepository;
import org.lsi.entities.Groupe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
@Service
public class GroupeMetierImpl implements GroupeMetier {
	@Autowired
    private GroupeRepository groupeRepository;
	
	@Override
	public Groupe saveGroupe(Groupe e) {
		
		return groupeRepository.save(e);
	}

	@Override
	public Page<Groupe> ListeGroupe(int page,int size) {
		return groupeRepository.findAll(PageRequest.of(page, size));
	}

}
