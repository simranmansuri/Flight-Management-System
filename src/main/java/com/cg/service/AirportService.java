package com.cg.service;

import java.math.BigInteger;
import java.util.List;
import com.cg.bean.Airport;

public interface AirportService {
	
	//view all airports
	public List<Airport> viewAirport();
	
	//view airport by airport code
	public Airport viewAirport(BigInteger code);
	
	//view airport by airport name
	public Airport viewAirportByName(String name);
	
	//view Airport by airport location
	public List<Airport> viewAirportByLocation(String location);
	
	//add airport
	public Airport addAirport(Airport airport);
	
	//modify airport
	public Airport modifyAirport(BigInteger code, Airport airport);
	
	//delete airport
	public void deleteAirport(BigInteger code);
	
	//patch airport
	public Airport patchAirport(BigInteger code, Airport airport);
	
}
