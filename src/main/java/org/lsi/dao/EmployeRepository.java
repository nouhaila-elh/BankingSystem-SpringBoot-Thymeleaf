package org.lsi.dao;

import org.lsi.entities.Employe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeRepository extends JpaRepository<Employe, Long> {
		public Employe findByCodeEmploye(Long codeEmploye);
}
