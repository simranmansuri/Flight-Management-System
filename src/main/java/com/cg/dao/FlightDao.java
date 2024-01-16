package com.cg.dao;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cg.bean.Flight;

public interface FlightDao extends JpaRepository<Flight, BigInteger>{

	public List<Flight> findByCarrierName(String cname);
}
