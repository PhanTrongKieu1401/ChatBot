package com.ChatBox.BTL.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ChatBox.BTL.DAO.IntentsDAOImpl;
import com.ChatBox.BTL.DAO.SampleDAOImpl;
import com.ChatBox.BTL.Model.Sample;

@Controller
public class SampleController {
	
	@Autowired
	IntentsDAOImpl intentsDAOImpl;
	
	@Autowired
	SampleDAOImpl sampleDAOImpl;
	
	@GetMapping("/intents/{id}/findSample")
	public String findSample(Model model,@RequestParam(name = "keyword") String keyword,@PathVariable String id) {
		int idIntents = Integer.valueOf(id);
		model.addAttribute("intents", intentsDAOImpl.getIntentByID(idIntents));
		model.addAttribute("LIST_SAMPLE", sampleDAOImpl.SearchSample(idIntents, keyword));
		return "admin/sample";
	}
	
	@GetMapping("/intents/{idIntents}/sample/{id}")
	public String getSample(Model model, @PathVariable("id") int id, @PathVariable("idIntents") int idIntents) {

	    if (id < 0) {
	        model.addAttribute("sample", new Sample());
	    } else {
	    	//get sample infor
	    }
	    model.addAttribute("intents", intentsDAOImpl.getIntentByID(idIntents));
	    model.addAttribute("id", id);
	    return "admin/sampleDetail";
	}
	
	@PostMapping("/intents/{idIntents}/sample/save/{id}")
	public String addSample(@PathVariable("idIntents") int idIntents,@ModelAttribute("sample") Sample sample){
		sampleDAOImpl.addSample(sample, idIntents);
		return "redirect:/intents/" + idIntents;
	}
}
