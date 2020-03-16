package com.badri.springboot.crudapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.badri.springboot.crudapi.dao.EmployeeDAO;
import com.badri.springboot.crudapi.entity.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeDAO empDAO;
	
	@Autowired //@Qualifier("employeeDAOJpaImpl") says use jpaimpl class
	//because we have two impl in this case ,
	//so qualifier use this and solves the problem of two beans found.
	//
	public EmployeeServiceImpl(@Qualifier("employeeDAOJpaImpl") EmployeeDAO empDAO) {
		this.empDAO = empDAO;
	}
	
	@Override
	@Transactional
	public List<Employee> findAll() {
	
		return empDAO.findAll();
	}

	@Override
	@Transactional
	public Employee findById(int id) {
		return empDAO.findById(id);
	}

	@Override
	@Transactional
	public void save(Employee emp) {
   
		empDAO.save(emp);
	}

	@Override
	@Transactional
	public void deleteById(int id) {
		empDAO.deleteById(id);
	}

}
