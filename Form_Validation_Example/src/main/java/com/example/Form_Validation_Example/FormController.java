package com.example.Form_Validation_Example;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class FormController {

	
	@GetMapping("/showForm")
	public String showForm(Model model) {
		
		Form form = new Form();
		
		model.addAttribute("form",form);
		
		System.out.println("Form class added successfully!");
		
		return "index";
		
	}
	
	
	@PostMapping("/getDetails")
	public String showDetails(@ModelAttribute("form") Form form) {
		
		System.out.println("Form data is "+form);
//		 System.out.println("✅ Form submitted: " + form);
		
		return "home";
		
	}
}
