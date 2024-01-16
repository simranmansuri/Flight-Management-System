package com.cg.bean;

import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Flight {

	@Id
	@SequenceGenerator(name="sequence3", initialValue=10013)		//Sequence generated from 10004 auto increment by 1
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="sequence3")
	@Column(name="fno")
	BigInteger flightNumber;
	
	@Column(name="cname")
	String carrierName;
	
	@Column(name="fmodel")
	String flightModel;
	
	@Column(name="seatcap")
	int seatCapacity;
	
	//Default unparameterized constructor
	public Flight() {
	}
	
	//Parameterized constructor
	public Flight(BigInteger flightNumber, String carrierName, String flightModel, int seatCapacity) {
		this.flightNumber = flightNumber;
		this.carrierName = carrierName;
		this.flightModel = flightModel;
		this.seatCapacity = seatCapacity;
	}
	
	//Getters & setters
	public BigInteger getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(BigInteger flightNumber) {
		this.flightNumber = flightNumber;
	}
	public String getCarrierName() {
		return carrierName;
	}
	public void setCarrierName(String carrierName) {
		this.carrierName = carrierName;
	}
	public String getFlightModel() {
		return flightModel;
	}
	public void setFlightModel(String flightModel) {
		this.flightModel = flightModel;
	}
	public int getSeatCapacity() {
		return seatCapacity;
	}
	public void setSeatCapacity(int seatCapacity) {
		this.seatCapacity = seatCapacity;
	}
	
	//toString method
	@Override
	public String toString() {
		return "Flight [flightNumber=" + flightNumber + ", carrierName=" + carrierName + ", flightModel=" + flightModel
				+ ", seatCapacity=" + seatCapacity + "]";
	}
	
	
	
}
