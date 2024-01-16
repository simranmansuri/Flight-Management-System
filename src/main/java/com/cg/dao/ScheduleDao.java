package com.cg.dao;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cg.bean.Schedule;

public interface ScheduleDao extends JpaRepository<Schedule, BigInteger>{
	
	public List<Schedule> findBySourceAirportAirportName(String name);
	
	public List<Schedule> findByDestinationAirportAirportName(String name);
	
}
  