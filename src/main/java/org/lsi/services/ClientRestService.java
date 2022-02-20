package org.lsi.services;

import java.util.List;

import org.lsi.entities.Client;
import org.lsi.metier.ClientMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@Controller
public class ClientRestService {
	
	@Autowired
	private ClientMetier clientMetier;

	@RequestMapping(value="/AddClient",method=RequestMethod.POST)
	public String saveClient(@ModelAttribute Client client) {
		clientMetier.saveClient(client);
		return "redirect:/clients";
	}
	
	
	@RequestMapping(value="/clients",method=RequestMethod.GET)
	public String AllClients(Model model,
			@RequestParam(name="page",defaultValue="0")int page,
			@RequestParam(name="size",defaultValue="3")int size) {
		Page<Client> allClients=clientMetier.listClient(page,size);
		model.addAttribute("allClients",allClients);
		model.addAttribute("CurrentPage",page);
		model.addAttribute("size",size);
		model.addAttribute("Pages",new int[allClients.getTotalPages()]);
		return "Client/AllClients";
	}
	
	
	@RequestMapping(value="/showAddClientForm",method=RequestMethod.GET)
	public String register(Model model) {
		model.addAttribute("client",new Client());
		return "Client/AddClient";
	}
}