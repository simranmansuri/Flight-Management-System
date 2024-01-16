package com.cg.bean;

import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class User {
	
	@Id
	@SequenceGenerator(name="sequence6", initialValue=31)			//Sequence generated from 15 auto increment by 1
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="sequence6")
	private BigInteger id;
	
	@Column(name="type")
	private String userType;
	
	@Column(name="name")
	private String userName;
	
	@Column(name="pwd")
	private String userPassword;
	
	@Column(name="phno")
	private BigInteger userPhone;
	
	private String email;
	
	//Default constructor
	public User() {
		
	}
	
	//Parameterized Constructor
	public User(String userType, BigInteger userId, String userName, String userPassword, BigInteger userPhone,
			String email) {
		this.userType = userType;
		this.id = userId;
		this.userName = userName;
		this.userPassword = userPassword;
		this.userPhone = userPhone;
		this.email = email;
	}

	//Getters and setters
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public BigInteger getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(BigInteger userPhone) {
		this.userPhone = userPhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	//toString method
	@Override
	public String toString() {
		return "User [userType=" + userType + ", userId=" + id + ", userName=" + userName + ", userPassword="
				+ userPassword + ", userPhone=" + userPhone + ", email=" + email + "]";
	}
	

}
