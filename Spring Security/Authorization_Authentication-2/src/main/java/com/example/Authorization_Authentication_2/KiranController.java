package com.example.Authorization_Authentication_2;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kiran")
public class KiranController {
	
	@Autowired
	KiranService kiranService;
	
	
	@PostMapping("/save")
	public Kiran saveData(@RequestBody Kiran kiran) {
		

		return kiranService.saveDetails(kiran);
		
	}
	
	@GetMapping("/getData")
	public List<Kiran> getData() {
		

		return kiranService.getAllDetails();
		
	}
	
	@GetMapping("/getDatabyId/{userid}")
	public Kiran getDataById(@PathVariable("userid") Integer userId) {
		

		return kiranService.getDetailsbyId(userId);
		
	}
	
	
	

}
