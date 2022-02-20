package org.lsi.services;

import java.util.Date;
import java.util.List;

import org.lsi.entities.Client;
import org.lsi.entities.Compte;
import org.lsi.entities.CompteCourant;
import org.lsi.entities.CompteEpargne;
import org.lsi.entities.Employe;
import org.lsi.metier.CompteMetier;
import org.lsi.metier.EmployeMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CompteRestService {
	
	@Autowired
	private CompteMetier compteMetier;
	@Autowired
	private EmployeMetier employeMetier;

	@RequestMapping(value="ajouterCompteCourant",method=RequestMethod.GET)
	public String registerCompteCourant(Model model) {
		
		CompteCourant cc = new CompteCourant();
		model.addAttribute("CompteCourant",cc);
		
		List<Employe> AllEmployes =employeMetier.listEmployes();
		model.addAttribute("AllEmployes",AllEmployes);
		
		return "Acounts/AddCompteCourant";
	}
	
	
	@RequestMapping(value="ConsulterCompte",method=RequestMethod.GET)
	public String ConsulterCompte(Model model) {
		return "Acounts/ConsulterCompte";
	}
	
	@RequestMapping(value="/SaveCC", method=RequestMethod.POST)
	public String saveCompteCourant(@ModelAttribute("CompteCourant") CompteCourant CompteCourant ) {
		compteMetier.saveCompte(CompteCourant);
		//return "redirect:/comptes/ + ${CompteCourant.codeCompte}";
		return "redirect:/ajouterCompteCourant";
	}
	
	
	@RequestMapping(value="/ajouterCompteEpargne",method=RequestMethod.GET)
	public String registerCompteEpargne(Model model) {
		
		CompteEpargne ce = new CompteEpargne();
		model.addAttribute("CompteEpargne",ce);
		
		List<Employe> AllEmployes =employeMetier.listEmployes();
		model.addAttribute("AllEmployes",AllEmployes);
		return "Acounts/AddCompteEpargne";
	}
	
	@RequestMapping(value="/SaveCE", method=RequestMethod.POST)
	public String saveCompteEpargne(@ModelAttribute("CompteEpargne") CompteEpargne CompteEpargne ) {
		compteMetier.saveCompte(CompteEpargne);
		return "redirect:/ajouterCompteEpargne";
	}
	
	@RequestMapping(value="/comptes", method=RequestMethod.GET)
	public String getOneAcount(@RequestParam (value="code")Long code,Model model) {
		
		Compte OneAcount= compteMetier.getCompte(code);
		model.addAttribute("OneAcount", OneAcount);
		return "Acounts/getOneAcount";
	}
	
	//get one acount
//	@RequestMapping(value="/comptes/{code}", method=RequestMethod.GET)
//	public String getOneAcount(@PathVariable Long code,Model model) {
//		Compte OneAcount= compteMetier.getCompte(code);
//		model.addAttribute("OneAcount", OneAcount);
//		return "Acounts/getOneAcount";
//	}
//	@RequestMapping(value="/AllClientAcounts/{code}", method=RequestMethod.GET)
//	public String getAllClientAcounts(@PathVariable String code,Model model) {
//		List<Compte>lc=compteMetier.findCompteByClient(code);
//		System.out.println(lc);
//		model.addAttribute("AllClientAcounts", lc);
//		return "Acounts/AllUserAcounts";
//	}
//	
	
	@RequestMapping(value="/AllClientAcounts", method=RequestMethod.GET)
	public String getAllClientAcounts(@RequestParam (value="code")Long code,Model model,
			@RequestParam(name="page",defaultValue="0")int page,
			@RequestParam(name="size",defaultValue="3")int size) {
		
		String A= String.valueOf(code);
		Page<Compte>lc=compteMetier.findCompteByClient(A,page,size);
		System.out.println(lc);
		model.addAttribute("AllClientAcounts", lc);
		model.addAttribute("Pages", new int[lc.getTotalPages()]);
		model.addAttribute("CurrentPage",page);
		model.addAttribute("size",size);
		model.addAttribute("Code",code);
		return "Acounts/AllUserAcounts";
	}
	
	@RequestMapping(value="ConsulterComptesClient",method=RequestMethod.GET)
	public String ConsulterComptesClient(Model model) {
		
		return "Acounts/ConsulterCompteClient";
	}
	

}
