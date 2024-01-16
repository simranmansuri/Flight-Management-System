package com.cg.service;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.bean.Booking;
import com.cg.bean.Passenger;
import com.cg.bean.ScheduledFlight;
import com.cg.dao.BookingDao;
import com.cg.dao.PassengerDao;
import com.cg.dao.ScheduledFlightDao;
import com.cg.exception.BookingNotFoundException;
import com.cg.exception.InvalidPassengerException;
import com.cg.exception.PassengerNotFoundException;

@Service("passengerService")
public class PassengerServiceImpl implements PassengerService {
	
	@Autowired
	PassengerDao passengerDao;
	
	@Autowired
	BookingDao bookingDao;
	
	@Autowired
	ScheduledFlightDao scheduledFlightDao;

	@Override
	public List<Passenger> viewPassenger() {
		return passengerDao.findAll();
	}

	@Override
	public Passenger viewPassenger(BigInteger pnrNumber) {
		Optional<Passenger> p = passengerDao.findById(pnrNumber);
		if (p.isEmpty()) {
			//throw exception if no passenger is found
			throw new PassengerNotFoundException("No passenger found with number "+pnrNumber);
		}
		return p.get();
	}

	@Override
	public List<Passenger> viewPassengerByName(String name) {
		List<Passenger> p = passengerDao.findByPassengerName(name);
		if (p.size()==0) {
			//throw exception if no passenger is found
			throw new PassengerNotFoundException("No passenger found with name "+name);
		}
		return p;
	}

	@Override
	public List<Passenger> viewPassengerByUIN(BigInteger uin) {
		List<Passenger> p = passengerDao.findByPassengerUIN(uin);
		if (p.size()==0) {
			//throw exception if no passenger is found
			throw new PassengerNotFoundException("No passenger found with uin "+uin);
		}
		return p;
	}
	
	@Override
	public List<Passenger> viewPassengerByBookingId(BigInteger bookingId) {
		Optional<Booking> book = bookingDao.findById(bookingId);
		if (book.isEmpty()) {
			throw new BookingNotFoundException("No booking exists for id "+bookingId);
		}
		Booking b = book.get();
		List<Passenger> plist = passengerDao.findByBooking(b);
		return plist;
	}
	
	@Transactional
	@Override
	public Passenger addPassenger(BigInteger bookingId, Passenger passenger) {
		Optional<Booking> t = bookingDao.findById(bookingId);
		if (t.isEmpty()) {
			throw new BookingNotFoundException("No booking found with id "+bookingId);
		}
		t.get().getPassengerList().add(passenger);
		Booking b= t.get();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		String formatDateTime = LocalDateTime.now().format(formatter); 
		LocalDateTime bookingDate = LocalDateTime.parse(formatDateTime, formatter);
		b.setBookingDate(bookingDate);
		int diff = b.getPassengerList().size() - b.getNoOfPassengers();
		ScheduledFlight sf = scheduledFlightDao.findById(b.getFlight().getSfid()).get();
		sf.setAvailableSeats(sf.getAvailableSeats()-diff);
		b.setNoOfPassengers(b.getPassengerList().size());
		b.setTicketCost(b.getNoOfPassengers());
		return passenger;
		
	}
	
	@Transactional
	@Override
	public Passenger modifyPassenger(BigInteger pnrNumber, Passenger passenger) {
		Optional<Passenger> pop = passengerDao.findById(pnrNumber);
		if (pop.isEmpty()) {
			//throw exception if no schedule is found
			throw new PassengerNotFoundException("No passenger found with number "+pnrNumber);
		}
		Passenger p = pop.get();
		p.setPassengerName(passenger.getPassengerName());
		p.setPassengerUIN(passenger.getPassengerUIN());
		p.setPassengerAge(passenger.getPassengerAge());
		p.setLuggage(passenger.getLuggage());
		return passengerDao.save(p);
	}
	
	@Transactional
	@Override
	public void deletePassenger(BigInteger pnrNumber) {
		Optional<Passenger> p = passengerDao.findById(pnrNumber);
		if (p.isEmpty()) {
			//throw exception if no passenger is found
			throw new PassengerNotFoundException("No passenger found with number "+pnrNumber);
		}
		Booking b = p.get().getBooking();
		b.getPassengerList().remove(p.get());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		String formatDateTime = LocalDateTime.now().format(formatter); 
		LocalDateTime bookingDate = LocalDateTime.parse(formatDateTime, formatter);
		b.setBookingDate(bookingDate);
		int diff = b.getPassengerList().size() - b.getNoOfPassengers();
		ScheduledFlight sf = scheduledFlightDao.findById(b.getFlight().getSfid()).get();
		sf.setAvailableSeats(sf.getAvailableSeats()-diff);
		b.setNoOfPassengers(b.getPassengerList().size());
		b.setTicketCost(b.getNoOfPassengers());
		passengerDao.deleteById(pnrNumber);
		
	}
	
	@Transactional
	@Override
	public Passenger patchPassenger(BigInteger pnrNumber, Passenger passenger) {
		Optional<Passenger> pop = passengerDao.findById(pnrNumber);
		if (pop.isEmpty()) {
			//throw exception if no passenger is found
			throw new PassengerNotFoundException("No passenger found with number "+pnrNumber);
		}
		Passenger p = pop.get();
		if (!passenger.getPassengerName().equals("empty")) {
			p.setPassengerName(passenger.getPassengerName());
		}
		if (passenger.getPassengerAge()!=0) {
			p.setPassengerAge(passenger.getPassengerAge());
		}
		if (passenger.getPassengerUIN()!=BigInteger.valueOf(0)) {
			p.setPassengerUIN(passenger.getPassengerUIN());
		}
		if (passenger.getLuggage()!=0) {
			p.setLuggage(passenger.getLuggage());
		}
		validatePassenger(p);
		return passengerDao.save(p);
	}

	@Override
	public void validatePassenger(Passenger passenger) {
		//UIN should be of 12 digits
		BigInteger uin = passenger.getPassengerUIN();
		Pattern p= Pattern.compile("^[1-9][0-9]{11}$");
		Matcher m=p.matcher(uin.toString());
		if(!m.find())
		{
			throw new InvalidPassengerException("Passenger UIN is invalid");
		}	
	}

}
