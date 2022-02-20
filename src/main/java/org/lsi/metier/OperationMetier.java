package org.lsi.metier;

import java.util.List;

import org.lsi.entities.Operation;
import org.springframework.data.domain.Page;

public interface OperationMetier {
	
	public boolean verser(Long code,double montant, Long codeEmp);
	public boolean retirer(Long code,double montant, Long codeEmp);
	public boolean virement(Long cpte1,Long cpte2,double solde, Long codeEmp);
	public Operation saveOperation(Operation e);
	public Page<Operation> AllAcountOperations(String code,int page,int size);
}
