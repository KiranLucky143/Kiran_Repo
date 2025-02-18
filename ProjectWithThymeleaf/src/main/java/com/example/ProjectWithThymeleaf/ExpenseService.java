package com.example.ProjectWithThymeleaf;

import java.util.List;

public interface ExpenseService {

	
	public Expense save(Expense expense);
	
	public List<Expense> getAllExpenses();
	
}
