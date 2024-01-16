package com.cg.dao;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.bean.Booking;
import com.cg.bean.Passenger;

public interface PassengerDao extends JpaRepository<Passenger, BigInteger> {
	
	public List<Passenger> findByPassengerName(String name);
	
	public List<Passenger> findByPassengerUIN(BigInteger uin);
	
	public List<Passenger> findByBooking(Booking booking);
}
