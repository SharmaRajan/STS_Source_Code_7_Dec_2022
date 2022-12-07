package com.example.Student.dto;

public class StudentDto {

	private String name;
	private String email;
	private long contactNo;
	
	public StudentDto()	{}
	
	public StudentDto(String name, String email, long contactNo) {
		super();
		this.name = name;
		this.email = email;
		this.contactNo = contactNo;
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
	public long getContactNo() {
		return contactNo;
	}
	public void setContactNo(long contactNo) {
		this.contactNo = contactNo;
	}

	@Override
	public String toString() {
		return "StudentDto [name=" + name + ", email=" + email + ", contactNo=" + contactNo + "]";
	}
}
