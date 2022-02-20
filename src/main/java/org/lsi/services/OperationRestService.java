package org.lsi.services;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.lsi.dao.OperationRepository;
import org.lsi.entities.Compte;
import org.lsi.entities.Employe;
import org.lsi.entities.Operation;
import org.lsi.entities.Retrait;
import org.lsi.entities.Versment;
import org.lsi.metier.CompteMetier;
import org.lsi.metier.EmployeMetier;
import org.lsi.metier.OperationMetier;
import org.lsi.metier.PageOperation;
//import org.lsi.metier.PageOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@Controller
public class OperationRestService {
	
	@Autowired
	OperationMetier operationMetier;
	@Autowired
	private EmployeMetier employeMetier;
	@Autowired
	private CompteMetier compteMetier;
	
	//@RequestMapping(value="/operations",method=RequestMethod.GET)
	// public PageOperation getOperation(
	//		@RequestParam String codeCompte,
	//		@RequestParam int page,
	//		@RequestParam int size) {
	//	return operationMetier.getOperation(codeCompte, page, size);
	//}
	
	
	
	
	
	@RequestMapping(value="/versement",method=RequestMethod.GET )
	public String verser(
			@RequestParam Long employe,
			@RequestParam Long compte,
			@RequestParam double montant ) {
		
	    operationMetier.verser(compte, montant, employe);	
		return "redirect:/";
	}
	@RequestMapping(value="/retrait",method=RequestMethod.GET) 
	public String retirer(
			@RequestParam Long employe,
			@RequestParam Long compte,
			@RequestParam double montant) {
		 operationMetier.retirer(compte, montant, employe);
		 
		 return "redirect:/";
	}
	
	@RequestMapping(value="/virement",method=RequestMethod.GET) 
	public String virement(
			@RequestParam(name="compte") List<Long> compte,
			@RequestParam double montant,
			@RequestParam Long employe) {
		operationMetier.virement(compte.get(0), compte.get(1), montant, employe);
		return "redirect:/";
	}
	
	
	
	
	@GetMapping("/showVersementForm")
	public String showOperationForm(Model model) {
		Operation operation = new Versment();
    	model.addAttribute("operation", operation);
    	
		List<Employe> AllEmployes =employeMetier.listEmployes();
		model.addAttribute("AllEmployes",AllEmployes);
		
		
		return "Operation/Versement";
	}
	
	
	
	@GetMapping("/showRetraitFrom")
	public String showRetraitForm(Model model) {
		Operation operation = new Retrait();
    	model.addAttribute("operation", operation);
    	
		List<Employe> AllEmployes =employeMetier.listEmployes();
		model.addAttribute("AllEmployes",AllEmployes);

		return "Operation/Retrait";
	}
	
	
	
	@GetMapping("/showVirementForm")
	public String showVirementForm(Model model) {
	
    	
    	Operation operation = new Operation();
    	model.addAttribute("operation", operation);
    	

		List<Employe> AllEmployes =employeMetier.listEmployes();
		model.addAttribute("AllEmployes",AllEmployes);

		return "Operation/Virement";
	}
	
//	@RequestMapping(value="/AllAcountOperations/{code}",method=RequestMethod.GET) 
//	public String AllAcountOperations(@PathVariable String code,Model model) {
//		List<Operation> lo=operationMetier.AllAcountOperations(code);
//		model.addAttribute("AllAcountOperations", lo);
//		return "Operation/AllAcountOperations";
//	} 
	
	@RequestMapping(value="/AllAcountOperations",method=RequestMethod.GET) 
	public String AllAcountOperations(@RequestParam (value="code")Long code,
			@RequestParam(name="page",defaultValue="0")int page,
			@RequestParam(name="size",defaultValue="3")int size,
			Model model) {
		String A= String.valueOf(code);
		Page<Operation> lo=operationMetier.AllAcountOperations(A,page,size);
		model.addAttribute("AllAcountOperations", lo);
		model.addAttribute("CurrentPage",page);
		model.addAttribute("size",size);
		model.addAttribute("Pages",new int[lo.getTotalPages()]);
		model.addAttribute("Code",code);
		
		return "Operation/AllAcountOperations";
	} 
	
	
	@RequestMapping(value="ConsulterOperationCompte",method=RequestMethod.GET)
	public String ConsulterOperationCompte(Model model) {
		
		return "Operation/ConsulterOperation";
	}
}
