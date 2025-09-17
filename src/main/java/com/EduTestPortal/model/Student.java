package com.EduTestPortal.model;

import java.sql.Timestamp;

public class Student {
	
	private int sid;
	private String name;
	private String email;
	private String phone;
	private String password;
	private String department;
	private int yearOfStudy;
	private String batch;
	private Timestamp registeredAt;
	
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	
	public int getYearOfStudy() {
		return yearOfStudy;
	}
	public void setYearOfStudy(int yearOfStudy) {
		this.yearOfStudy = yearOfStudy;
	}
	public String getBatch() {
		return batch;
	}
	public void setBatch(String batch) {
		this.batch = batch;
	}
	public Timestamp getRegisteredAt() {
		return registeredAt;
	}
	public void setRegisteredAt(Timestamp registeredAt) {
		this.registeredAt = registeredAt;
	}
	
	
	
	

}
