package com.badri.springboot.crudapi.dao;

import java.util.List;

import com.badri.springboot.crudapi.entity.Employee;

//dao interface
public interface EmployeeDAO {

	public List<Employee> findAll();
	
	public Employee findById(int id) ;
	
	public void save(Employee emp);
	
	public void deleteById(int id);
	
	
}
