package com.cg.dao;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cg.bean.Airport;

public interface AirportDao extends JpaRepository<Airport, BigInteger>{

	public Airport findByAirportName(String name);
	
	public List<Airport> findByAirportLocation(String location);

}
