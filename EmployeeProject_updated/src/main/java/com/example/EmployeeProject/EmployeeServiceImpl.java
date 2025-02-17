package com.example.EmployeeProject;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	EmployeeRepository employeeRepository;
	
	
	//saving employees
	
	@Override
	public Employee save(Employee emp) {
		
	Employee savedEmployee =employeeRepository.save(emp);
	
		return savedEmployee;
	}

	
	//LIsting all employees
	@Override
	public List<Employee> getAllEmployees() {
		
	
		return employeeRepository.findAll();
	}



	//Deletion
	@Override
	public String deleteAll() {
		
		employeeRepository.deleteAll();
		
		return "Employee deleted successfully!";
	}


	@Override
	public Employee findByID(Integer id) {
		
		
		return employeeRepository.findById(id).get();
	}


	@Override
	public String deleteByID(Integer id) {
		
		employeeRepository.deleteById(id);
		
		return "Deleted Successfully!";
	}


	@Override
	public List<Employee> findBySalaryLessThan(Integer sal) {
		
		
		return employeeRepository.findBySalaryLessThan(sal);
	}


	@Override
	public List<Employee> findByEmployeesStartingWith(String name) {
		
		
		return employeeRepository.findByEmpNameStartingWith(name);
	}


	@Override
	public List<Employee> findDataByBandAndExperience(String band_type, Integer exp) {
		
		
		return employeeRepository.findByBandAndExperience(band_type, exp);
	}

	
}
