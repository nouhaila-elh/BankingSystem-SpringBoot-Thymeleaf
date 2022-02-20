package org.lsi.metier;

import java.util.List;

import org.lsi.dao.EmployeRepository;
import org.lsi.entities.Employe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class EmployeMtierImpl implements EmployeMetier {
	
	@Autowired
	private EmployeRepository  employeRepository;

	@Override
	public Employe saveEmploye(Employe e) {
		// TODO Auto-generated method stub
		return employeRepository.save(e);
	}

	@Override
	public Page<Employe> listEmployes(int page,int size) {
		// TODO Auto-generated method stub
		return employeRepository.findAll(PageRequest.of(page, size));
	}

	@Override
	public Employe findByCodeEmploye(Long id) {
		// TODO Auto-generated method stub
		return employeRepository.findByCodeEmploye(id);
	}

	@Override
	public List<Employe> listEmployes() {
		// TODO Auto-generated method stub
		return employeRepository.findAll();
	}

}
