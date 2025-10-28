package com.example.ProjectWithThymeleaf;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/expense")
public class ExpenseController {

	
	@Autowired
	ExpenseService expenseService;
	
	@GetMapping("/show")
	public String showForm(Model model) {
		
		model.addAttribute("expense", new Expense());
		
		return "expense-form";
		
	}
	
	@PostMapping("/expenseInfo")
	public String expenseInfo(@ModelAttribute Expense expense, Model model) {
	    expenseService.save(expense); // Save expense when form is submitted
	    System.out.println("Expense saved!");

	    model.addAttribute("message", "Expense recorded: " + expense.getExpenseAmount() + " Type: " + expense.getExpenseType());

	    List<Expense> exp_list = expenseService.getAllExpenses();
	    model.addAttribute("expenseList", exp_list);

	    return "result"; // Ensure result.html exists
	}

}
