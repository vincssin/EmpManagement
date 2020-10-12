package com.employe.api.model;
 
import java.util.HashMap;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
 
// Mongo database annotation.
@Document //(collection= "Employee")
public class Employee {

	@Transient
    public static final String SEQUENCE_NAME = "users_sequence";
	
	@Id
	private long id;
	
	
    public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}



	
	private String empId;
    
    private String empName="";
    private String empGender="";
    private String empDesignation="";
    private String empDepartment="";
    
    
    

	public String getEmpId() {
		return empId;
	}


	public void setEmpId(String empId) {
		this.empId = empId;
	}


	public String getEmpName() {
		return empName;
	}



	public void setEmpName(String empName) {
		this.empName = empName;
	}


	public String getEmpGender() {
		return empGender;
	}


	public void setEmpGender(String empGender) {
		this.empGender = empGender;
	}


	public String getEmpDesignation() {
		return empDesignation;
	}


	public void setEmpDesignation(String empDesignation) {
		this.empDesignation = empDesignation;
	}



	public String getEmpDepartment() {
		return empDepartment;
	}


	public void setEmpDepartment(String empDepartment) {
		this.empDepartment = empDepartment;
	}




  private EmployeeAddress empAddress;
//  private EmployeeContact empContact;
  
	public EmployeeAddress getEmpAddress() {
		return empAddress;
	}


	public void setEmpAddress(EmployeeAddress empAddress) {
		this.empAddress = empAddress;
	}


//	public EmployeeContact getEmpContact() {
//		return empContact;
//	}
//
//
//	public void setEmpContact(EmployeeContact empContact) {
//		this.empContact = empContact;
//	}



	private Map<String, String> empContacts = new HashMap<>();


	public Map<String, String> getEmpContacts() {
		return empContacts;
	}

	public void setEmpContacts(Map<String, String> empContacts) {
		this.empContacts = empContacts;
	}

	@Override
    public String toString() {
        return "Emp {EmpId=" + empId +
        		", EmpName=" + empName +
        		", EmpGender=" + empGender +
        		", EmpDesignation=" + empDesignation +
        		", EmpDepartment=" + empDepartment +
        		", empAddress=" + empAddress +
        		", empContact=" + empContacts +
        		"}";
    }
    
//	", empContact=" + empContact +

}