package com.example.demo.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Employee;
import com.example.demo.exception.BusinessException;
import com.example.demo.repos.EmployeeCrudRepo;

@Service
public class EmployeeService implements EmployeeServiceInterface
{
	@Autowired
	private EmployeeCrudRepo crudRepo;

	@Override
	public Employee addEmployee(Employee employee)
	{
		try {
			if(employee.getName().isEmpty() || employee.getName().length()==0)
			{
				throw new BusinessException("601","please send valid name!!!!!!!!!!!!!!");

			}
			Employee savedEmployee = crudRepo.save(employee);
			return savedEmployee;
			
		    } catch (IllegalArgumentException e) {
			throw new BusinessException("602", "given employee is null"+e.getMessage());
			
		    }
		    catch (Exception e) {
		    	throw new BusinessException("603", "something is wrong in service layer"+e.getMessage());
		    }
		
		
		
	}

	@Override
	public List<Employee> getAllEmployees() 
	{
		try {
			List<Employee>empList=crudRepo.findAll();
			if(empList.isEmpty())
			throw new BusinessException("604", "hey list is completely empty");
			return empList;

		   } 
		catch (Exception e)
		{
	    	throw new BusinessException("605", "something is wrong in service layer in fetching all employee"+e.getMessage());

		}
	}

	@Override
	public Employee getEmpById(Long empidL)
	{
		try {
			   return crudRepo.findById(empidL).get();
		    }
			catch (IllegalArgumentException e) 
			{
				throw new BusinessException("606", "given employee is null"+e.getMessage());
		    } 
			catch (NoSuchElementException e) 
			{
	          throw new BusinessException("607", "given employee is not present in DB"+e.getMessage());
			}

	}

	@Override
	public void deleteEmpById(Long empidL) 
	{
		try 
		{
			crudRepo.deleteById(empidL);
		} 
		catch (IllegalArgumentException e) 
		{
			throw new BusinessException("608", "given employee is null"+e.getMessage());
	    } 
		catch (Exception e)
		{
	    	throw new BusinessException("610", "something is wrong in service layer in fetching all employee"+e.getMessage());

		}
	}
}
