package com.cg.service;

import java.math.BigInteger;
import java.util.List;

import com.cg.bean.Passenger;

public interface PassengerService {
	
	//view all passengers
	public List<Passenger> viewPassenger();
	
	//view passenger by passenger number
	public Passenger viewPassenger(BigInteger pnrNumber);
		
	//view passenger by passenger name
	public List<Passenger> viewPassengerByName(String name);
		
	//view passenger by passenger uin
	public List<Passenger> viewPassengerByUIN(BigInteger uin);
	
	//view passenger by booking
	public List<Passenger> viewPassengerByBookingId(BigInteger bookingId);
		
	//add passenger
	public Passenger addPassenger(BigInteger bookingId, Passenger passenger);
		
	//modify schedule
	public Passenger modifyPassenger(BigInteger pnrNumber, Passenger passenger);
	
	//delete passenger
	public void deletePassenger(BigInteger pnrNumber);
		
	//patch passenger
	public Passenger patchPassenger(BigInteger pnrNumber, Passenger passenger);
	
	//validate schedule
	public void validatePassenger(Passenger passenger);
}
