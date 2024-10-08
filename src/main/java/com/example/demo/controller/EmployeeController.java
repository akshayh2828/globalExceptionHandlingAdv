package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Employee;
import com.example.demo.exception.BusinessException;
import com.example.demo.exception.ControllerException;
import com.example.demo.service.EmployeeServiceInterface;
@RestController
@RequestMapping("/code")
@CrossOrigin(origins = "http://localhost:3000")  // Allow requests from React frontend
public class EmployeeController 
{

	@Autowired
	private EmployeeServiceInterface employeeServiceInterface;
	
	@PostMapping("/save")
	public ResponseEntity<?> addEmployee(@RequestBody Employee employee)
	{
		try {		
			Employee employeeSaved = employeeServiceInterface.addEmployee(employee);
			return new ResponseEntity<Employee>(employeeSaved, HttpStatus.CREATED);


			
		} catch (BusinessException e) {
			ControllerException ce=new ControllerException(e.getErrorCode(), e.getErrorMessage());
			return new ResponseEntity<ControllerException>(ce, HttpStatus.CREATED);

		}
		catch (Exception e) 
		{
			ControllerException ce=new ControllerException("611","somthing wrong in controller");
			return new ResponseEntity<ControllerException>(ce, HttpStatus.CREATED);
		}
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Employee>> getAllEmployees(){
		
		List<Employee> listOfAllEmps = employeeServiceInterface.getAllEmployees();
		return new ResponseEntity<List<Employee>>(listOfAllEmps, HttpStatus.OK);
	}
	
	@GetMapping("/emp/{empid}")
	public ResponseEntity<?> getEmpById(@PathVariable("empid") Long empidL){
		
		try {
			Employee empRetrieved = employeeServiceInterface.getEmpById(empidL);
			return new ResponseEntity<Employee>(empRetrieved, HttpStatus.OK);
			
		} 
		catch (BusinessException e) {
			ControllerException ce=new ControllerException(e.getErrorCode(), e.getErrorMessage());
			return new ResponseEntity<ControllerException>(ce, HttpStatus.CREATED);

		}
		catch (Exception e) 
		{
			ControllerException ce=new ControllerException("611","somthing wrong in controller");
			return new ResponseEntity<ControllerException>(ce, HttpStatus.CREATED);
		}
		
	}
	
	@DeleteMapping("/delete/{empid}")
	public ResponseEntity<?> deleteEmpById(@PathVariable("empid") Long empidL){
		
		try {
			employeeServiceInterface.deleteEmpById(empidL);
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
			
		} 
		catch (BusinessException e) {
			ControllerException ce=new ControllerException(e.getErrorCode(), e.getErrorMessage());
			return new ResponseEntity<ControllerException>(ce, HttpStatus.CREATED);

		}
		catch (Exception e) 
		{
			ControllerException ce=new ControllerException("612","somthing wrong in controller");
			return new ResponseEntity<ControllerException>(ce, HttpStatus.CREATED);
		}
		
	}
	
	@PutMapping("/update")
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee){
		Employee employeeSaved = employeeServiceInterface.addEmployee(employee);
		return new ResponseEntity<Employee>(employeeSaved, HttpStatus.CREATED);
	}
}
