package com.genesis.employee.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.genesis.employee.data.EmployeeRepository;
import com.genesis.employee.model.Employee;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	private EmployeeRepository employeeRepository;
	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	public EmployeeController(com.genesis.employee.data.EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@GetMapping(value = "/{id}", produces = "application/json")
	public Employee getEmployeeDetails(@PathVariable("id") long id) {
		LOGGER.info("GET request for employee id : {}", id);
		Employee employee = employeeRepository.findEmployeeById(id);
		if (employee == null) {
			employee = new Employee(0, "N/A");
		}
		return employee;
	}

}
