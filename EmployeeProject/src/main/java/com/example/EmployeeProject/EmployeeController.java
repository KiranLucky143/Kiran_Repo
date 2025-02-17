package com.example.EmployeeProject;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/emp")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;
	
	@PostMapping("/save")
	public Employee saveEmployee(@RequestBody Employee employee) {
		
		System.out.println("Received Employee: " + employee); // Debugging
		
		Employee savedEmployee =employeeService.save(employee);
		
		return savedEmployee;
	}
	
	@GetMapping("/getAll")
	public List<Employee> FindAllEmployees() {
		
		
		return employeeService.getAllEmployees();
	}
	
	
	@GetMapping("/findByID")
	public Employee FindEmployeesByID(@RequestParam(name = "empID") Integer EmpID) {
		
		
		return employeeService.findByID(EmpID);
	}
	
	
	@DeleteMapping("/deleteByID")
	public String DeleteEmployeesByID(@RequestParam(name = "empID") Integer EmpID) {
		
		
		return employeeService.deleteByID(EmpID);
	}
	
	
	@GetMapping("/getEmpsBySalary/{salary}")
	public List<Employee> getEmpsBySalaryLessThan(@PathVariable("salary") Integer salary) {
		
		
		return employeeService.findBySalaryLessThan(salary);
	}
	
}
