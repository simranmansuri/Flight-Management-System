package com.cg.bean;

import java.math.BigInteger;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class ScheduledFlight {
	
	@Id
	@SequenceGenerator(name="sequence5", initialValue=114)			//Sequence generated from 100 auto increment by 1
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="sequence5")
	BigInteger sfid;
	
	@OneToOne
	Flight flight;
	
	@OneToOne(cascade=CascadeType.ALL)
	Schedule schedule;
	
	@Column(name="avalseat")
	int availableSeats;

	//Default constructor
	public ScheduledFlight() {
		
	}

	//Parameterized Constructor
	public ScheduledFlight(BigInteger sfid, Flight flight, Schedule schedule, int availableSeats) {
		this.sfid = sfid;
		this.flight = flight;
		this.schedule = schedule;
		this.availableSeats = availableSeats;
	}

	//Getters and setters
	public BigInteger getSfid() {
		return sfid;
	}

	public void setSfid(BigInteger sfid) {
		this.sfid = sfid;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	public int getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}

	//toString method
	@Override
	public String toString() {
		return "ScheduledFlight [sfid=" + sfid + ", flight=" + flight + ", schedule=" + schedule + ", availableSeats="
				+ availableSeats + "]";
	}


}
