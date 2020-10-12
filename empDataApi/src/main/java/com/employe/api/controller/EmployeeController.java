package com.employe.api.controller;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.employe.api.model.Employee;
import com.employe.api.service.Employeeservice;

@RestController
@RequestMapping(value = "/")
public class EmployeeController {
	@Autowired
	Employeeservice empService;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	// TEST ROOT

	@RequestMapping(value = "/aaa", method = RequestMethod.GET)
	public ResponseEntity getAllUsers() {
//    	logger.debug("get user.");
//		return "vvvvvv";
		
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CACHE_CONTROL, "no-cache");

		return ResponseEntity.ok()
					   .headers(headers)
					   .body("SUCCESS");
	}

	/** Method to save employees in the db.*/

	@RequestMapping(value = "/addall", method = RequestMethod.POST)
//    @PostMapping(value= "/addall")
	public String create(@RequestBody List<Employee> emp) {
		logger.debug("Saving employees.");
		empService.createEmployees(emp);
		return "Employee records created.";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST,  produces = MediaType.APPLICATION_JSON_VALUE)
//  @PostMapping(value= "/addall")
	public Object create(@RequestBody Employee emp) {
		logger.debug("Saving employees.");
		
		return empService.createEmployee(emp);
	}

	/**Method to fetch all employees from the db.*/
//    @GetMapping(value= "/getall")
	@RequestMapping(value = "/getall", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE) //
	public Object getAll() {
		logger.debug("Getting all employees.");
		Collection<Employee> obj = empService.getAllEmployees();
		if(obj.isEmpty()) {
//			return "{message\":\"No Data Found\"}";
			
//			 return Collections.singletonMap("message", "No Data Found");
			 
			 HashMap<String, String> map = new HashMap<>();
			 map.put("message", "No Data Found");
//			 map.put("foo", "bar");
			 return map;
		}
		return obj;
	}

	/**
	 * Method to fetch employee by id.
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/get/{employee-id}")
	public Object getById(@PathVariable(value = "employee-id") String id) {
		logger.debug("Getting employee with employee-id= {}.", id);
		return empService.findEmployeeByempId(id);
	}

	/**
	 * Method to update employee by id.
	 * 
	 * @param id
	 * @param e
	 * @return
	 */
	@PutMapping(value = "/update/{employee-id}")
	public Object update(@PathVariable(value = "employee-id") String id, @RequestBody Employee e) {
		logger.debug("Updating employee with employee-id= {}.", id);
//		e.setEmpId(id);
		
		return empService.updateEmployee(e, id);
	}

	/**
	 * Method to delete employee by id.
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/delete/{employee-id}")
	public Object delete(@PathVariable(value = "employee-id") String id) {
		logger.debug("Deleting employee with employee-id= {}.", id);
		
//		return "Employee record for employee-id= " + id + " deleted.";
		
		return empService.deleteEmployeeByempId(id);
	}

	/**
	 * Method to delete all employees from the db.
	 * 
	 * @return
	 */
	@DeleteMapping(value = "/deleteall")
	public String deleteAll() {
		logger.debug("Deleting all employees.");
		empService.deleteAllEmployees();
		return "All employee records deleted.";
	}
}