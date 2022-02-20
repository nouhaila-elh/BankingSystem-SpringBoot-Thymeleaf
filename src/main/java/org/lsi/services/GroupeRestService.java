package org.lsi.services;

import java.util.List;

import org.lsi.entities.Groupe;
import org.lsi.metier.GroupeMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GroupeRestService {
	@Autowired
	private GroupeMetier groupeMetier;
	
	@GetMapping("/")
	public String viewHomePage(Model model) {
		return "pagePrincipale";
	}
	
	@RequestMapping(value="/listeGroupe",method=RequestMethod.GET)
	public String ListeGroupe(Model model,
			@RequestParam(name="page",defaultValue="0")int page,
			@RequestParam(name="size",defaultValue="3")int size) {
		model.addAttribute("listGroupe", groupeMetier.ListeGroupe(page,size));
		model.addAttribute("CurrentPage",page);
		model.addAttribute("size",size);
		model.addAttribute("Pages",new int[groupeMetier.ListeGroupe(page,size).getTotalPages()]);
		return "Groupe/AllGroupes";
	}
	
	@GetMapping("/showAddGroupeForm")
    public String showAddGroupeForm(Model model) {
    	Groupe groupe = new Groupe();
    	model.addAttribute("groupe", groupe);
		return "Groupe/AddGroupe";
    }
	
	@PostMapping("/saveGroupe")
	    public String saveGroupe(@ModelAttribute("groupe") Groupe groupe) {
		groupeMetier.saveGroupe(groupe);
	    return"redirect:/listeGroupe";
	    }
	

}




