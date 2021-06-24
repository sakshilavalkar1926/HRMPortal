package com.hrmportal.entity;

import java.sql.Date;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;


/*
 * @author sakshilavalkar
 * This class defines Employee model.
 */
public class Employee {

	
	@CsvBindByName
	private int empCode;
	@CsvBindByName
	private String empName;
	@CsvBindByName
	private String empLocation;
	@CsvBindByName
	private String empEmail;
	
	@CsvDate(value = "dd-MM-yyyy")
	@CsvBindByName
	private Date empDOB;
	
	
	/*
	 * Getters and Setters
	 */
	
	
	public int getEmpCode() {
		return empCode;
	}
	public void setEmpCode(int empCode) {
		this.empCode = empCode;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	
	public String getEmpLocation() {
		return empLocation;
	}
	public void setEmpLocation(String empLocation) {
		this.empLocation = empLocation;
	}
	public String getEmpEmail() {
		return empEmail;
	}
	public void setEmpEmail(String empEmail) {
		this.empEmail = empEmail;
	}
	public Date getEmpDOB() {
		return empDOB;
	}
	public void setEmpDOB(Date empDOB) {
		this.empDOB = empDOB;
	}

	/*
	 * ToString method
	 */
	@Override
	public String toString() {
		return "Employee [empCode=" + empCode + ", empName=" + empName + ", emplocation=" + empLocation
				+ ", empEmail=" + empEmail + ", empDOB=" + empDOB + "]";
	}
}
