package com.example.ProjectWithThymeleaf;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Expense {

	
	
	//runtimeOnly 'com.mysql:mysql-connector-j'
	//implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer expenseId;
	
	private Integer expenseAmount;
	
	private String expenseType;

	@Override
	public String toString() {
		return "Expense [expenseId=" + expenseId + ", expenseAmount=" + expenseAmount + ", expenseType=" + expenseType
				+ "]";
	}

	public Integer getExpenseId() {
		return expenseId;
	}

	public void setExpenseId(Integer expenseId) {
		this.expenseId = expenseId;
	}

	public Integer getExpenseAmount() {
		return expenseAmount;
	}

	public void setExpenseAmount(Integer expenseAmount) {
		this.expenseAmount = expenseAmount;
	}

	public String getExpenseType() {
		return expenseType;
	}

	public void setExpenseType(String expenseType) {
		this.expenseType = expenseType;
	}
	
	
	
}
