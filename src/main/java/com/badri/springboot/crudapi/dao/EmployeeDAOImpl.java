package com.badri.springboot.crudapi.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.badri.springboot.crudapi.entity.Employee;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {
	
	private EntityManager entityManager;
	
	//set up constructor injection 
	@Autowired
	public EmployeeDAOImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}

	@Override
   //@Transactional added to the service layer
	public List<Employee> findAll() {

		//get the hibernate session
		Session session = entityManager.unwrap(Session.class);
		
		//create the query
		
		Query<Employee> theQuery = session.createQuery("from Employee",Employee.class);
		
		//execute the query
		List<Employee> result = theQuery.getResultList();
		//return the result
		
		return result;
	
	}

	@Override
	public Employee findById(int id) {
	
	Session session = entityManager.unwrap(Session.class);
	
	Employee emp = session.get(Employee.class , id);
		
		
		return emp;
	}

	@Override
	public void save(Employee employee) {
	
		Session session = entityManager.unwrap(Session.class);
		
		session.saveOrUpdate(employee);
		
	}

	@Override
	public void deleteById(int theId) {
	
		Session session = entityManager.unwrap(Session.class);
		
		//delete emp of id 
		
		Query q = session.createQuery("delete from Employee where id=:empId");
			
		q.setParameter("empId", theId);
        q.executeUpdate();
	}
  
}
