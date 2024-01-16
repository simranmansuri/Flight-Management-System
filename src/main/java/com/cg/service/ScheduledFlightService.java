package com.cg.service;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

import com.cg.bean.ScheduledFlight;


public interface ScheduledFlightService {
	
	//add a scheduled flight
	public ScheduledFlight scheduleFlight(ScheduledFlight scft);
	
	//view all scheduled flights for a flight number
	public List<ScheduledFlight> viewScheduledFlights(BigInteger fno);
	
	//view all scheduled flights
	public List<ScheduledFlight> viewScheduledFlight();
	
	//modify a scheduled flight
	public ScheduledFlight modifyScheduledFlight(BigInteger sfid, ScheduledFlight scheduledFlight);
	
	//delete a scheduled flight
	public void deleteScheduledFlight(BigInteger sfid);
	
	//validate scheduled flight
	public void validateScheduledFlight(ScheduledFlight scft);
	
	//view all scheduled flights between two airports on a certain date
	public List<ScheduledFlight> viewScheduledFlights(String src, String dst, LocalDate date);
	
	//patch a scheduled flight
	public ScheduledFlight patchScheduledFlight(BigInteger sfid, ScheduledFlight scheduledFlight);
}
