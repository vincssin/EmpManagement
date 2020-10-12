package com.employe.api.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.employe.api.model.Employee;
import com.employe.api.mongo.dao.Employeedao;
import com.employe.api.service.SequenceGeneratorService;

@Service
public class Employeeserviceimpl implements Employeeservice {
	@Autowired
	SequenceGeneratorService empSeqService;
	private HashMap<String, Object> map = new HashMap<>();

	// The dao repository will use the Mongodb-Repository to perform the database operations.
	@Autowired
	Employeedao dao;
	
	@Autowired
	private MongoTemplate mongoTemplate;

	/* (non-Javadoc)
	 * @see com.employe.api.service.Employeeservice#createEmployee(java.util.List)
	 */
	@Override
	public void createEmployees(List<Employee> emp) {
		System.out.println("createEmployees");
//		dao.save(emp);
		dao.save(emp);
	}
	/* (non-Javadoc)
	 * @see com.employe.api.service.Employeeservice#createEmployee(Employee Object)
	 */
	@Override
	public Object createEmployee(Employee emp) {
		map =new HashMap<>();
		System.out.println("******createEmployee********");
		System.out.println("EmpId: "+emp.getEmpId());
		System.out.println("Emp Data: "+emp.toString());
		boolean isExist =false;
		
		if(emp.getEmpId().toString().isEmpty()) {
			 map.put("message", "EmpId required Please check and try again");
		}else if(emp.getEmpId().toString().length()!=4) {
			 map.put("message", "EmpId invalid Please check and try again");
		}else {
		
//			Object existObj =dao.exists(emp.getEmpId());
//			System.out.println("existObj: "+existObj);
			
			Query query = new Query();
			query.addCriteria(Criteria.where("empId").is(emp.getEmpId()));
			Object existObj = mongoTemplate.findOne(query, Employee.class);
			
			System.out.println("existObj: "+existObj);
			
			if(existObj!=null) {
				isExist = true;
			}
			
			 System.out.println("ISEXIST: "+isExist);
			 if(isExist) {
				 map.put("message", "Employee "+emp.getEmpId()+ " already Exist");
			 }else {
				 emp.setId(empSeqService.generateSequence(Employee.SEQUENCE_NAME));
				 dao.save(emp);
				 map.put("message", "Employee "+emp.getEmpId()+ " added successfully");
			 }
			
		}
		
//		emp.setId(empSeqService.generateSequence(Employee.SEQUENCE_NAME));
//		 dao.save(emp);	
		return map;
	}

	/* (non-Javadoc)
	 * @see com.employe.api.service.Employeeservice#getAllEmployees()
	 */
	@Override
	public Collection<Employee> getAllEmployees() {
		Collection<Employee> obj = dao.findAll();
		return obj;
	}

	/* (non-Javadoc)
	 * @see com.employe.api.service.Employeeservice#findEmployeeById(int)
	 */
	@Override
	public Object findEmployeeByempId(String empId) {
//		return dao.findOne(empId);

		map =new HashMap<>();
		Query query = new Query();
		query.addCriteria(Criteria.where("empId").is(empId));
		Object existObj = mongoTemplate.findOne(query, Employee.class);
		
		System.out.println("existObj: "+existObj);
		
		if(existObj!=null) {
			 map.put("data", existObj);
		}else {
			 map.put("message", "Employe ID "+empId+" not found");
		}
		return map;
	}

	/* (non-Javadoc)
	 * @see com.employe.api.service.Employeeservice#deleteEmployeeById(int)
	 */
	@Override
	public Object deleteEmployeeByempId(String empId) {
//		dao.delete(empId);
		
		map =new HashMap<>();
		Query query = new Query();
		query.addCriteria(Criteria.where("empId").is(empId));
		Object existObj = mongoTemplate.findOne(query, Employee.class);
		
		System.out.println("existObj: "+existObj);
		
		if(existObj!=null) {
			 mongoTemplate.remove(existObj);
			 map.put("message", "Employee "+empId+" deleted successfully");
		}else {
			 map.put("message", "Employee "+empId+" not found");
		}
		return map;
	}

	/* (non-Javadoc)
	 * @see com.employe.api.service.Employeeservice#updateEmployee(int)
	 */
	@Override
	public Object updateEmployee(Employee emp, String empId) {
//		dao.save(emp);
		
		map =new HashMap<>();
		Query query = new Query();
		query.addCriteria(Criteria.where("empId").is(empId));
		Object existObj = mongoTemplate.findOne(query, Employee.class);
		
		System.out.println("existObj: "+existObj);
		
		if(existObj!=null) {
			emp.setEmpId(empId);
			Employee existEmp =(Employee)existObj;
			emp.setId(existEmp.getId());
			dao.save(emp);
			 map.put("message", "Employee "+empId+" updated successfully");
		}else {
			 map.put("message", "Employee "+empId+" not found");
		}
		return map;
	}

	/* (non-Javadoc)
	 * @see com.employe.api.service.Employeeservice#deleteAllEmployees()
	 */
	@Override
	public void deleteAllEmployees() {
		dao.deleteAll();
	}
}