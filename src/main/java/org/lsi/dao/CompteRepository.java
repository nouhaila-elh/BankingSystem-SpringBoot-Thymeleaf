package org.lsi.dao;

import java.util.List;

import org.lsi.entities.Compte;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CompteRepository extends JpaRepository<Compte, Long> {
	

	@Query(value="select * from compte c inner join client cl where c.code_cli = cl.code_client and c.code_cli =?1",
			nativeQuery=true) 
	public Page<Compte> findCompteByClient(String code, Pageable pageable);
}
