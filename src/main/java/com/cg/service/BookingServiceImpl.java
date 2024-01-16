package com.cg.service;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cg.bean.Airport;
import com.cg.bean.Booking;
import com.cg.bean.Passenger;
import com.cg.bean.ScheduledFlight;
import com.cg.bean.User;
import com.cg.dao.AirportDao;
import com.cg.dao.BookingDao;
import com.cg.dao.ScheduledFlightDao;
import com.cg.dao.UserDao;
import com.cg.exception.BookingNotFoundException;
import com.cg.exception.InvalidBookingException;
import com.cg.exception.ScheduledFlightNotFoundException;


@Service("bookingService")
public class BookingServiceImpl implements BookingService
{
	
	@Autowired
	BookingDao bookingDao;
	
	@Autowired
	AirportDao airportDao;
	
	@Autowired
	ScheduledFlightDao scheduledFlightDao;
	
	@Autowired
	UserDao userDao;
	
	@Transactional
	@Override
	public Booking addBooking(Booking booking) {	
		//Booking date time set to system date time
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		String formatDateTime = LocalDateTime.now().format(formatter); 
		LocalDateTime bookingDate = LocalDateTime.parse(formatDateTime, formatter);
		booking.setBookingDate(bookingDate);
		booking.setTicketCost(booking.getNoOfPassengers());
		//change available seats after adding a booking
		ScheduledFlight sf = scheduledFlightDao.findById(booking.getFlight().getSfid()).get();
		sf.setAvailableSeats(sf.getAvailableSeats()-booking.getNoOfPassengers());
		return bookingDao.save(booking);
	
	}

	@Transactional
	@Override
	public Booking modifyBooking(BigInteger id,Booking booking) {
		Optional<Booking> opbook = bookingDao.findById(id);
		if(opbook.isEmpty())
		{
			//throw exception if no booking found
			throw new BookingNotFoundException("No booking found for booking id : "+booking.getBookingId());
		}
		Booking b = opbook.get();
		//Booking date time set to system date time
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		String formatDateTime = LocalDateTime.now().format(formatter); 
		LocalDateTime bookingDate = LocalDateTime.parse(formatDateTime, formatter);
		booking.setBookingDate(bookingDate);
		b.setPassengerList(booking.getPassengerList());
		booking.setTicketCost(booking.getNoOfPassengers());
		//change available seats after modifying booking
		int diff = booking.getNoOfPassengers() - b.getNoOfPassengers();
		ScheduledFlight sf = scheduledFlightDao.findById(booking.getFlight().getSfid()).get();
		sf.setAvailableSeats(sf.getAvailableSeats()-diff);
		b.setNoOfPassengers(booking.getNoOfPassengers());
		b.setTicketCost(b.getNoOfPassengers());
		return bookingDao.save(booking);
	}

	@Override
	public Booking viewBooking(BigInteger id) {
		Optional<Booking> opbook = bookingDao.findById(id);
		if(opbook.isEmpty())
		{
			//throw exception if no booking found
			throw new BookingNotFoundException("No booking found for booking id : "+id);
		}
		return opbook.get();
		
	}

	
	@Override
	public List<Booking> viewBooking() {
		
		return bookingDao.findAll();
	}

	@Transactional
	@Override
	public void deleteBooking(BigInteger id) {
		Optional<Booking> opbook = bookingDao.findById(id);
		if(opbook.isPresent())
		{
			Booking booking = opbook.get();
			//change available seats after deleting booking
			ScheduledFlight sf = scheduledFlightDao.findById(booking.getFlight().getSfid()).get();
			sf.setAvailableSeats(sf.getAvailableSeats()+booking.getNoOfPassengers());
			bookingDao.delete(booking);
		}
		else
		{
			//throw exception if no booking found
			throw new BookingNotFoundException("No booking found for booking id : "+id);
		}
		
	}
	
	
	@Override
	public void validateBooking(Booking booking) {
		int nop = booking.getNoOfPassengers();
		int availableSeats = scheduledFlightDao.findById(booking.getFlight().getSfid()).get().getAvailableSeats();
		//no of passengers should be <= 4 & available seats
		if(nop > availableSeats || nop>4 || nop<1 || nop!=booking.getPassengerList().size())
		{
			throw new InvalidBookingException("Number of passengers are invalid");
		}
		
		//scheduled flight should be present in the database
		List<ScheduledFlight> sflist = scheduledFlightDao.findAll();
		if(sflist.stream().noneMatch(sf -> sf.getSfid().equals(booking.getFlight().getSfid()))) 
		{
			throw new InvalidBookingException("No flight scheduled for id "+booking.getFlight().getSfid());
		}
		
		//arrival & departure date time > current date time 
		if(booking.getFlight().getSchedule().getArrivalTime().compareTo(LocalDateTime.now())<0 
				|| booking.getFlight().getSchedule().getDepartureTime().compareTo(LocalDateTime.now())<0)
		{
			throw new InvalidBookingException("Date and time has already elapsed");
		}
		
		//arrival > departure date time
		if (booking.getFlight().getSchedule().getArrivalTime().compareTo(booking.getFlight().getSchedule().getDepartureTime())<0)
		{
			throw new InvalidBookingException("Arrival time should be greater than the departure time");
		}
		
		//Destination & source airport should be there in the database
		List<Airport> a1 = airportDao.findAll();
		if(a1.stream().noneMatch(a -> a.getAirportCode().equals(booking.getFlight().getSchedule().getSourceAirport().getAirportCode())) || 
		a1.stream().noneMatch(a -> a.getAirportCode().equals(booking.getFlight().getSchedule().getDestinationAirport().getAirportCode())))
		{
			throw new InvalidBookingException("Airport does not exist in the database");
		}
		
		//Destination & source airport should not be same
		if (booking.getFlight().getSchedule().getDestinationAirport().getAirportCode().equals(booking.getFlight().getSchedule().getSourceAirport().getAirportCode())) 
		{
			throw new InvalidBookingException("Destination airport should not be same as source airport");
		}
		
		//user should be present in the database
		List<User> u1 = userDao.findAll();
		if (u1.stream().noneMatch(u -> u.getId().equals(booking.getUser().getId())))
		{
			throw new InvalidBookingException("No user found with id "+booking.getUser().getId());
		}
		//Validate each passenger in the passenger list
		for(Passenger p:booking.getPassengerList())
		{
			validatePassenger(p);
		}
		
	}

	@Override
	public void validatePassenger(Passenger passenger) {
		//UIN should be of 12 digits
		BigInteger uin = passenger.getPassengerUIN();
		Pattern p= Pattern.compile("^[1-9][0-9]{11}$");
		Matcher m=p.matcher(uin.toString());
		if(!m.find())
		{
			throw new InvalidBookingException("Passenger UIN is invalid");
		}	
	}
	
	@Override
	@Transactional
	public Booking patchBooking(BigInteger id,Booking booking)
	{
		Optional<Booking> opbook = bookingDao.findById(id);
		if(opbook.isEmpty())
		{
			//throw exception if no booking found
			throw new BookingNotFoundException("No booking found for booking id : "+booking.getBookingId());
		}
		Booking b = opbook.get();
		if(booking.getNoOfPassengers()!=0)
		{
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
			String formatDateTime = LocalDateTime.now().format(formatter); 
			LocalDateTime bookingDate = LocalDateTime.parse(formatDateTime, formatter);
			b.setBookingDate(bookingDate);
			b.setPassengerList(booking.getPassengerList());
			//change available seats after modifying booking
			int diff = booking.getNoOfPassengers() - b.getNoOfPassengers();
			ScheduledFlight sf = scheduledFlightDao.findById(b.getFlight().getSfid()).get();
			sf.setAvailableSeats(sf.getAvailableSeats()-diff);
			b.setNoOfPassengers(booking.getNoOfPassengers());
			b.setTicketCost(b.getNoOfPassengers());
			
		}
		return bookingDao.save(b);
	}
	
	@Override
	public List<Passenger> viewBookingBySfid(BigInteger sfid)
	{
		Optional<ScheduledFlight> opbook = scheduledFlightDao.findById(sfid);
		if(opbook.isEmpty())
		{
			//throw exception if no booking found
			throw new ScheduledFlightNotFoundException("No scheduled flight found for schedule flight id : "+sfid);
		}
		List<Booking> b = bookingDao.findByFlightSfid(sfid);
		
		if(b.isEmpty())
		{
			throw new BookingNotFoundException("No booking found for scheduled flight id "+sfid);
		}
		List<Passenger> p = new ArrayList<>();
		for(Booking book: b) {
			for (Passenger pass: book.getPassengerList()) {
				p.add(pass);
			}
		}
		return p;
	}
	
	@Override
	public void validatePatchBooking(Booking booking) {
		int nop = booking.getNoOfPassengers();
		int availableSeats = scheduledFlightDao.findById(booking.getFlight().getSfid()).get().getAvailableSeats();
		//no of passengers should be <= 4 & available seats
		if(nop > availableSeats || nop>4 || nop<1 || nop!=booking.getPassengerList().size())
		{
			throw new InvalidBookingException("Number of passengers are invalid");
		}
	}
	
	
}
