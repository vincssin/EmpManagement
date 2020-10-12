package com.employe.api.model;

public class EmployeeContact {
 private String contact_Number;
 private String mail_Id;
 
 @Override
 public String toString() {
     return "EmpContact [contact_Number=" + contact_Number +
     		", mail_Id=" + mail_Id +
     		"]";
 }
}
