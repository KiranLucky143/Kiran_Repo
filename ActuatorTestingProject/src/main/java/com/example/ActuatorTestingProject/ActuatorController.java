package com.example.ActuatorTestingProject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ActuatorController {

	@GetMapping("/hello")
	public String sayHello() {
		
		return "Hello";
		
	}
	
	@GetMapping("/submit")
	public String Submit() {
		
		return "submit-form";
		
	}
	

}
