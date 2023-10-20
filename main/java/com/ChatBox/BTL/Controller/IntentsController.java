package com.ChatBox.BTL.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ChatBox.BTL.DAO.IntentsDAOImpl;
import com.ChatBox.BTL.DAO.SampleDAOImpl;
import com.ChatBox.BTL.Model.Intents;
import com.ChatBox.BTL.Model.Sample;


@Controller
public class IntentsController {
	
	@Autowired
	IntentsDAOImpl intentsDAOImpl;
	
	@Autowired
	SampleDAOImpl sampleDAOImpl;


	@GetMapping("/home")
	public String home() {
		return "admin/serverHome";
	}
	
	@GetMapping("/manageSentence")
	public String getIntents(Model model) {
		model.addAttribute("LIST_INTENTS", intentsDAOImpl.getAllIntents());
		return "admin/ManageSentenceTemplate";
	}
	
	@GetMapping("/intents/{id}")
	public String getIntents(Model model,@PathVariable String id) {
	    int idIntents = Integer.parseInt(id);
	    if (idIntents < 0) {
	        model.addAttribute("intents", new Intents());
	    } else {
	        model.addAttribute("intents", intentsDAOImpl.getIntentByID(idIntents));
	        model.addAttribute("LIST_SAMPLE", sampleDAOImpl.getSampleByIntents(idIntents));
	        return "admin/sample";
	    }
	    model.addAttribute("id", idIntents);
	    return "admin/intents";
	}
	
	@PostMapping("/intents/save/{id}")
	public String addIntents(@ModelAttribute("intents") Intents intents){
		intentsDAOImpl.addIntents(intents);
		return "redirect:/manageSentence";
	}
	
	@GetMapping("/manageSentence/findIntents")
	public String findIntents(Model model,@RequestParam(name = "keyword") String keyword) {
		model.addAttribute("LIST_INTENTS", intentsDAOImpl.searchIntents(keyword));
		return "admin/ManageSentenceTemplate";
	}
	//------------------------------------------------------------------------------------------------------------
	
}
