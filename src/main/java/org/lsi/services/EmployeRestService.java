package org.lsi.services;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.lsi.dao.EmployeRepository;
import org.lsi.dao.GroupeRepository;
import org.lsi.entities.Employe;
import org.lsi.entities.Groupe;
import org.lsi.metier.EmployeMetier;
import org.lsi.metier.GroupeMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class EmployeRestService {
	
	@Autowired
	private EmployeMetier employeMetier;
	@Autowired
	private GroupeMetier groupeMetier;
	@Autowired
	private EmployeRepository employeRepository;
	@Autowired
	private GroupeRepository groupeRepository;
	
	
	@GetMapping("/showAddEmployeForm")
    public String showAddEmployeForm(Model model) {
    	Employe employe = new Employe();
    	model.addAttribute("employe", employe);
    	
    	List<Groupe> groupe = (List<Groupe>) groupeRepository.findAll(); 
	    model.addAttribute("allgroupes", groupe);
    	
		List<Employe> AllEmployes =employeMetier.listEmployes();
		model.addAttribute("AllEmployes",AllEmployes);
		return "Employes/AddEmploye";
    }

	@RequestMapping(value="/AddEmploye",method=RequestMethod.POST)
	public Employe saveEmploye(@RequestBody Employe e) {
		return employeMetier.saveEmploye(e);
	}

	/*@RequestMapping(value="/employes",method=RequestMethod.GET)
	public List<Employe> listEmployes() {
		return employeMetier.listEmployes();
	}*/
	@RequestMapping(value="/employes",method=RequestMethod.GET)
	public String listEmployes(Model model ,
			@RequestParam(name="page",defaultValue="0")int page,
			@RequestParam(name="size",defaultValue="3")int size) {
		Page<Employe> AllEmployes =employeMetier.listEmployes(page,size);
		model.addAttribute("AllEmployes",AllEmployes);
		model.addAttribute("CurrentPage",page);
		model.addAttribute("size",size);
		model.addAttribute("Pages",new int[AllEmployes.getTotalPages()]);
		return "Employes/AllEmployes";
	}
	
	
	@PostMapping("/saveEmploye")
    public String SaveEmploye(@ModelAttribute("employe") Employe employe) {
		employeMetier.saveEmploye(employe);
    return"redirect:/employes";
    }
	
	@GetMapping("/showAffectationForm")
    public String showAffectationForm(Model model) {
		Employe employe = new Employe();
    	model.addAttribute("employe", employe);
    	
		List<Employe> AllEmployes =employeMetier.listEmployes();
		model.addAttribute("AllEmployes",AllEmployes);
		
		List<Groupe> groupe = (List<Groupe>) groupeRepository.findAll(); 
	    model.addAttribute("allRoles", groupe);
		return "Employes/Affectation";
		
    }
	
	
	@PostMapping("/saveAffectation")
    public String SaveAffectation(@ModelAttribute("employe") Employe employe) {
	
	  Optional<Employe> emp= employeRepository.findById(employe.getCodeEmploye());
		
	
      return"redirect:/employes";
    }
	
	

}
