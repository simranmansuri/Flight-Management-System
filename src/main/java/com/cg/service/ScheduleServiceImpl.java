package com.cg.service;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.bean.Airport;
import com.cg.bean.Schedule;
import com.cg.dao.AirportDao;
import com.cg.dao.ScheduleDao;
import com.cg.exception.InvalidScheduleException;
import com.cg.exception.ScheduleNotFoundException;

@Service("scheduleService")
public class ScheduleServiceImpl implements ScheduleService {

	@Autowired
	ScheduleDao scheduleDao;
	@Autowired
	AirportDao airportDao;

	@Override
	public List<Schedule> viewSchedule() {
		return scheduleDao.findAll();
	}

	@Override
	public Schedule viewSchedule(BigInteger sid) {
		Optional<Schedule> sc = scheduleDao.findById(sid);
		if (sc.isEmpty()) {
			//throw exception if no schedule is found
			throw new ScheduleNotFoundException("No schedule found with code "+sid);
		}
		return sc.get();
	}

	@Override
	public List<Schedule> viewScheduleBySourceName(String name) {
		List<Schedule> s = scheduleDao.findBySourceAirportAirportName(name);
		if (s.size()==0) {
			//throw exception if no schedule is found
			throw new ScheduleNotFoundException("No schedule found for source "+name);
		}
		return s;
	}

	@Override
	public List<Schedule> viewScheduleByDestinationName(String name) {
		List<Schedule> s = scheduleDao.findByDestinationAirportAirportName(name);
		if (s.size()==0) {
			//throw exception if no schedule is found
			throw new ScheduleNotFoundException("No schedule found for destination "+name);
		}
		return s;
	}

	@Override
	public List<Schedule> viewScheduleByArrivalDate(LocalDate arrival) {
		List<Schedule> s1 = scheduleDao.findAll();
		List<Schedule> s2 = new ArrayList<>();
		
		for(Schedule s : s1)
		{
			if(arrival.compareTo(s.getArrivalTime().toLocalDate())==0)
			{
				//add scheduled flight in the list if it is between the given airports & available seats !=0
				s2.add(s);
			}
		}
		if (s2.size()==0) 
		{
			
			//throw exception if no schedule found
			throw new ScheduleNotFoundException("No schedule found for "+arrival);
		}
		return s2;
	}

	@Override
	public List<Schedule> viewScheduleByDepartureDate(LocalDate departure) {
		List<Schedule> s1 = scheduleDao.findAll();
		List<Schedule> s2 = new ArrayList<>();
		
		for(Schedule s : s1)
		{
			if(departure.compareTo(s.getDepartureTime().toLocalDate())==0)
			{
				//add scheduled flight in the list if it is between the given airports & available seats !=0
				s2.add(s);
			}
		}
		if (s2.size()==0) {
			
			//throw exception if no schedule found
			throw new ScheduleNotFoundException("No schedule found on "+departure);
		}
		return s2;
	}
	
	@Transactional
	@Override
	public Schedule addSchedule(Schedule schedule) {
		
		return scheduleDao.save(schedule);
	}
	
	@Transactional
	@Override
	public Schedule modifySchedule(BigInteger sid, Schedule schedule) {
		Optional<Schedule> sop = scheduleDao.findById(sid);
		if (sop.isEmpty()) 
		{
			//throw exception if no schedule is found
			throw new ScheduleNotFoundException("No schedule found with id "+sid);
		}
		Schedule s = sop.get();
		s.setSourceAirport(airportDao.findById(schedule.getSourceAirport().getAirportCode()).get());
		s.setDestinationAirport(airportDao.findById(schedule.getDestinationAirport().getAirportCode()).get());
		s.setArrivalTime(schedule.getArrivalTime());
		s.setDepartureTime(schedule.getDepartureTime());
		return scheduleDao.save(s);
	}

	@Transactional
	@Override
	public void deleteSchedule(BigInteger sid) {
		Optional<Schedule> sop = scheduleDao.findById(sid);
		if (sop.isEmpty()) {
			//throw exception if no schedule is found
			throw new ScheduleNotFoundException("No schedule found with id "+sid);
		}
		scheduleDao.deleteById(sid);
	}

	@Transactional
	@Override
	public Schedule patchSchedule(BigInteger sid, Schedule schedule) {
		Optional<Schedule> sop = scheduleDao.findById(sid);
		if (sop.isEmpty()) {
			//throw exception if no schedule is found
			throw new ScheduleNotFoundException("No schedule found with id "+sid);
		}
		Schedule s = sop.get();
		if (schedule.getSourceAirport().getAirportCode()!=BigInteger.valueOf(0)) {
			Airport src = airportDao.findById(schedule.getSourceAirport().getAirportCode()).get();
			if (src==null) {
				throw new InvalidScheduleException("Airport does not exist in the database");
			}
			s.setSourceAirport(src);
		}
		if (schedule.getDestinationAirport().getAirportCode()!=BigInteger.valueOf(0)) {
			Airport dst = airportDao.findById(schedule.getDestinationAirport().getAirportCode()).get();
			if (dst==null) {
				throw new InvalidScheduleException("Airport does not exist in the database");
			}
			s.setDestinationAirport(dst);
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		String datetime = "1970-01-01T00:00:00";
		LocalDateTime formatted = LocalDateTime.parse(datetime, formatter);
		if (schedule.getArrivalTime().compareTo(formatted)!=0) {
			s.setArrivalTime(schedule.getArrivalTime());
		}
		if (schedule.getDepartureTime().compareTo(formatted)!=0) {
			s.setDepartureTime(schedule.getDepartureTime());
		}
		validateSchedule(s);
		return scheduleDao.save(s);
	}

	@Override
	public void validateSchedule(Schedule schedule) {
		
		//arrival & departure date time > current date time 
		if(schedule.getArrivalTime().compareTo(LocalDateTime.now())<0 || 
			schedule.getDepartureTime().compareTo(LocalDateTime.now())<0 )
		{
			throw new InvalidScheduleException("Date time entered has already elapsed");
		}
				
		//arrival > departure date time
		if (schedule.getArrivalTime().compareTo(schedule.getDepartureTime())<0)
		{
			throw new InvalidScheduleException("Arrival time should be greater than the departure time");
		}
		//Destination & source airport should be there in the database
		List<Airport> a1 = airportDao.findAll();
		if(a1.stream().noneMatch(a -> a.getAirportCode().equals(schedule.getSourceAirport().getAirportCode())) || 
				a1.stream().noneMatch(a -> a.getAirportCode().equals(schedule.getDestinationAirport().getAirportCode())))
		{
			throw new InvalidScheduleException("Airport does not exist in the database");
		}
				
		//Destination & source airport should not be same
		if (schedule.getDestinationAirport().getAirportCode().equals(schedule.getSourceAirport().getAirportCode())) 
		{
			throw new InvalidScheduleException("Destination airport should not be same as source airport");
		}

	}

}
