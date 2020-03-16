package com.badri.springboot.crudapi.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.badri.springboot.crudapi.entity.Employee;
import com.badri.springboot.crudapi.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

	// field
	private EmployeeService empService;

	// use any injection any you like
	@Autowired
	public EmployeeRestController(EmployeeService empService) {
		this.empService = empService;
	}

	// get all emps
	@GetMapping("/employees")
	public List<Employee> findAll() {

		return empService.findAll();
	}

	// get particular emp
	@GetMapping("/employees/{employeeId}")
	public Employee getEmployee(@PathVariable int employeeId) {

		Employee emp = empService.findById(employeeId);

		if (emp == null) {
			throw new RuntimeException("employee not found");
		}

		return emp;
	}

	// add emp post mapping
	@PostMapping("/employees")
	// pass as json request body

	public Employee addEmployee(@RequestBody Employee theEmp) {

		// if they pass id in json set it to 0 so hib can save it.
		// otherwise it will increment id in db as always.
		theEmp.setId(0);
		empService.save(theEmp);

		return theEmp;
	}

	// update emp PUT mapping

//  @RequestMapping(value = "/employees/{theEmp}", 
//                        produces = "application/json", 
//                        method=RequestMethod.PUT)
	@PostMapping("/employees/{theEmp}")
	// pass as JSON request body
	public Employee updateEmployee(@RequestBody Employee theEmp) {
	  
		empService.save(theEmp);
		return theEmp;
	}

  //delete emp by id
  @DeleteMapping("/employees/{theId}")
  public String deleteEmployee(@PathVariable int theId) {

	  Employee theEmp = empService.findById(theId);
	  if(theEmp == null) {
		  throw new RuntimeException("employee not found " + theId);
	  }
	
	  empService.deleteById(theId);
	  return "deleted emp id " + theId;
  }
}
