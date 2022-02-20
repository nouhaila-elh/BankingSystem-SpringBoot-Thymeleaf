package org.lsi.dao;


import java.util.List;

import org.lsi.entities.Compte;
import org.lsi.entities.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.jdbc.repository.query.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;

public interface OperationRepository extends JpaRepository<Operation, Long> {


	@Query(value="SELECT * FROM operation o inner join compte c where o.code_cpte=c.code_compte AND c.code_compte=?1 ",nativeQuery=true)
	public Page<Operation> AllAcountOperations(String code,Pageable pageable);
}
