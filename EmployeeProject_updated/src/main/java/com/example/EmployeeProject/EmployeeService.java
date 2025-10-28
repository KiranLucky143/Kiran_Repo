package com.example.EmployeeProject;

import java.util.List;

import org.springframework.stereotype.Service;


public interface EmployeeService {

	
	public Employee save(Employee emp);
	
	public List<Employee> getAllEmployees();

	

	public String deleteAll();
	
	
	public Employee findByID(Integer id);
	
	public String deleteByID(Integer id);
	
	public List<Employee> findBySalaryLessThan(Integer sal);
	
	
	public List<Employee> findByEmployeesStartingWith(String name);
	
	public List<Employee> findDataByBandAndExperience(String band_type,Integer exp);
}
