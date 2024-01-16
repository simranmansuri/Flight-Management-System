package com.cg.bean;

import java.math.BigInteger;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import io.swagger.annotations.ApiModelProperty;

@Entity
public class Schedule {
	
	@Id
	@SequenceGenerator(name="sequence4", initialValue=114)			//Sequence generated from 100 auto increment by 1
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="sequence4")
	BigInteger sid;
	
	@ManyToOne
	@JoinColumn(name = "source_code", nullable = false)
	Airport sourceAirport;
	
	@ManyToOne
	@JoinColumn(name = "destination_code", nullable = false)
	Airport destinationAirport;
	
	//specify how to format datetime acc to SimpleDateTime format
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", shape = Shape.STRING)
	@ApiModelProperty(required = true, example = "2021-08-20T00:00:00")
	@Column(name="atime")
	LocalDateTime arrivalTime;
	
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", shape = Shape.STRING)
	@ApiModelProperty(required = true, example = "2021-08-20T00:00:00")
	@Column(name="dtime")
	LocalDateTime departureTime;
	
	//Default constructor
	public Schedule() {
		
	}
	
	//Parameterized Constructor
	public Schedule(BigInteger sid, Airport sourceAirport, Airport destinationAirport, LocalDateTime arrivalTime,LocalDateTime departureTime) {
		this.sid = sid;
		this.sourceAirport = sourceAirport;
		this.destinationAirport = destinationAirport;
		this.arrivalTime = arrivalTime;
		this.departureTime = departureTime;
	}

	//Getters and setters
	public BigInteger getSid() {
		return sid;
	}

	public void setSid(BigInteger sid) {
		this.sid = sid;
	}

	public Airport getSourceAirport() {
		return sourceAirport;
	}

	public void setSourceAirport(Airport sourceAirport) {
		this.sourceAirport = sourceAirport;
	}

	public Airport getDestinationAirport() {
		return destinationAirport;
	}

	public void setDestinationAirport(Airport destinationAirport) {
		this.destinationAirport = destinationAirport;
	}

	public LocalDateTime getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(LocalDateTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public LocalDateTime getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(LocalDateTime departureTime) {
		this.departureTime = departureTime;
	}

	//toString method
	@Override
	public String toString() {
		return "Schedule [sid=" + sid + ", sourceAirport=" + sourceAirport + ", destinationAirport="
				+ destinationAirport + ", arrivalTime=" + arrivalTime + ", departureTime=" + departureTime + "]";
	}
	
}
