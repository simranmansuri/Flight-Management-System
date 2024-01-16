package com.cg.bean;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Airport {
	
	@Id
	@SequenceGenerator(name="sequence7", initialValue=116)			//Sequence generated from 100 auto increment by 1
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="sequence7")
	@Column(name="code")
	BigInteger airportCode;

	@Column(name="name")
	String airportName;
	
	@Column(name="location")
	String airportLocation;
	
	//Default unparameterized constructor
	public Airport() {
		
	}
	
	//Parameterized constructor
	public Airport(BigInteger airportCode,String airportName,String airportLocation) {
		this.airportCode = airportCode;
		this.airportName = airportName;
		this.airportLocation = airportLocation;
	}

	//Getters & setters
	public BigInteger getAirportCode() {
		return airportCode;
	}

	public void setAirportCode(BigInteger airportCode) {
		this.airportCode = airportCode;
	}

	public String getAirportName() {
		return airportName;
	}

	public void setAirportName(String airportName) {
		this.airportName = airportName;
	}

	public String getAirportLocation() {
		return airportLocation;
	}

	public void setAirportLocation(String airportLocation) {
		this.airportLocation = airportLocation;
	}

	//toString method
	@Override
	public String toString() {
		return "Airport [airportCode=" + airportCode + ", airportName=" + airportName + ", airportLocation="
				+ airportLocation + "]";
	}

	
	
}
