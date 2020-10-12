package com.employe.api.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.employe.api.model.Employee;

public interface Employeeservice {

	/**
	 * Method to create new employees in the db using mongo-db repository.
	 * @param emp
	 */
	public void createEmployees(List<Employee> emp);
	
	/**
	 * Method to create new employee in the db using mongo-db repository.
	 * @param emp
	 */
	public Object createEmployee(Employee emp);

	/**
	 * Method to fetch all employees from the db using mongo-db repository.
	 * @return
	 */
	public Collection<Employee> getAllEmployees();

	/**
	 * Method to fetch employee by id using mongo-db repository.
	 * @param id
	 * @return
	 */
	public Object findEmployeeByempId(String empId);

	/**
	 * Method to delete employee by id using mongo-db repository.
	 * @param id
	 */
	public Object deleteEmployeeByempId(String empId);

	/**
	 * Method to update employee by id using mongo-db repository.
	 * @param id
	 */
	public Object updateEmployee(Employee emp, String id);

	/**
	 * Method to delete all employees using mongo-db repository.
	 */
	public void deleteAllEmployees();
}