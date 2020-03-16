package com.badri.springboot.crudapi.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.badri.springboot.crudapi.entity.Employee;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO {

private EntityManager entityManager;
	
	//set up constructor injection 
	@Autowired
	public EmployeeDAOJpaImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}
	
	@Override
	public List<Employee> findAll() {
		
				//create the query
				
				Query theQuery = (Query) entityManager.createQuery("from Employee");
				
				//execute the query
				List<Employee> result = theQuery.getResultList();
				//return the result
			
				return result;
	}

	@Override
	public Employee findById(int id) {
		
		//get the employee
		Employee theEmp  = entityManager.find(Employee.class, id);
		
		return theEmp;
	}

	@Override
	public void save(Employee emp) {

		Employee theEmp = entityManager.merge(emp);
		theEmp.setId(theEmp.getId());
		
	}

	@Override
	public void deleteById(int id) {

	Query query = (Query) entityManager.createQuery("delete from Employee where id=:id");
	query.setParameter("id", id);
	query.executeUpdate();
	
	}

}
