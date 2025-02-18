package com.example.ProjectWithThymeleaf;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpenseServiceImpl implements ExpenseService{

	
	@Autowired
	ExpenseRepository expenseRepository;
	
	@Override
	public Expense save(Expense expense) {
		
		System.out.println("Saved Successfully!");
		return expenseRepository.save(expense);
	}

	@Override
	public List<Expense> getAllExpenses() {
		
		return expenseRepository.findAll();
	}

	
	
}
