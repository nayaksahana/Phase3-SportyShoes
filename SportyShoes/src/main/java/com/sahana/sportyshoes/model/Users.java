package com.sahana.sportyshoes.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;  


@Entity
@Table(name= "users")   
public class Users { 


	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	
	@Column(name = "fname")
	private String fname;
	
	@Column(name = "lname")
	private String lname;
	
	@Column(name = "address")
	private String address;

	@Column(name = "email")
	private String email;

	@Column(name = "pwd")
	private String pwd;
	
	public Users() {
		super();
	}

	public Users(String fname, String lname, String address, String email, String pwd) {
		super();
	
		this.fname = fname;
		this.lname = lname;
		this.address = address;
		this.email = email;
		this.pwd = pwd;
	}

	public Users(int userId, String fname, String lname, String address, String email, String pwd) {
		super();
	
		this.userId = userId;
		this.fname = fname;
		this.lname = lname;
		this.address = address;
		this.email = email;
		this.pwd = pwd;
	}

	public int getUserId() {
		return userId;
	}

	

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	@Override
	public String toString() {
		return "User [UserId=" + userId + ", fname=" + fname + ", lname=" + lname + ", address=" + address + ","
				+ ", email=" + email + ", pwd=" + pwd + "]";
	}

}
