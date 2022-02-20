package org.lsi.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CE")

public class CompteEpargne extends Compte {
	
	private double taux;

	public double getTaux() {
		return taux;
	}

	public void setTaux(double taux) {
		this.taux = taux;
	}

	public CompteEpargne(Long codeCompte, Date dateCreation, double solde, double taux) {
		super(codeCompte, dateCreation, solde);
		this.taux = taux;
	}

	public CompteEpargne() {
		super();
		// TODO Auto-generated constructor stub
	}

}
