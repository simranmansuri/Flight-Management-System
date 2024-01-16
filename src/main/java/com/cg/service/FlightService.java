package com.cg.service;

	
import java.math.BigInteger;
import java.util.List;
import com.cg.bean.Flight;

public interface FlightService {
	
	//add a flight
	public Flight addFlight(Flight flight);
	
	//modify a flight
	public Flight modifyFlight(BigInteger fno, Flight flight);
	
	//view flight by flight number
	public Flight viewFlight(BigInteger fn);
	
	//view all flights
	public List<Flight> viewFlight();
	
	//delete flight by flight number
	void deleteFlight(BigInteger fn);
	
	//view all flights by carrier name
	public List<Flight> viewFlightByCarrierName(String cname);
	
	//patch a flight
	public Flight patchFlight(BigInteger fno, Flight flight);

}
