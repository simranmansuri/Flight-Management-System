package com.cg.service;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

import com.cg.bean.Schedule;

public interface ScheduleService {
	
	//view all schedules
	public List<Schedule> viewSchedule();
		
	//view schedule by schedule id
	public Schedule viewSchedule(BigInteger sid);
		
	//view schedule by source airport name
	public List<Schedule> viewScheduleBySourceName(String name);
		
	//view schedule by destination airport name
	public List<Schedule> viewScheduleByDestinationName(String name);
	
	//view schedule by arrival date
	public List<Schedule> viewScheduleByArrivalDate(LocalDate arrival);
	
	//view schedule by departure date
	public List<Schedule> viewScheduleByDepartureDate(LocalDate departure);
		
	//add schedule
	public Schedule addSchedule(Schedule schedule);
		
	//modify schedule
	public Schedule modifySchedule(BigInteger sid, Schedule schedule);
	
	//delete schedule
	public void deleteSchedule(BigInteger sid);
		
	//patch schedule
	public Schedule patchSchedule(BigInteger sid, Schedule schedule);
	
	//validate schedule
	public void validateSchedule(Schedule schedule);
}
