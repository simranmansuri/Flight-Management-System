package com.cg.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cg.exception.*;

@RestControllerAdvice
public class GlobalExceptionAdvice {
	
	//Handler for Airport Not Found Exception
	@ExceptionHandler(AirportNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String airportNotFoundHandler(AirportNotFoundException e) {
		return e.getMessage();
	}
	
	//Handler for User Not Found Exception
	@ExceptionHandler(UserNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String userNotFoundHandler(UserNotFoundException e) {
		return e.getMessage();
	}
	
	//Handler for Invalid User Exception
	@ExceptionHandler(InvalidUserException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	String userInvalidHandler(InvalidUserException e) {
		return e.getMessage();
	}
	
	//Handler for Scheduled Flight Not Found Exception
	@ExceptionHandler(ScheduledFlightNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String scheduledFlightNotFoundHandler(ScheduledFlightNotFoundException e) {
		return e.getMessage();
	}
	
	//Handler for Invalid Scheduled Flight Exception
	@ExceptionHandler(InvalidScheduledFlightException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	String scheduledFlightInvalidHandler(InvalidScheduledFlightException e) {
		return e.getMessage();
	}
	
	//Handler for Flight Not Found Exception
	@ExceptionHandler(FlightNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String flightNotFoundHandler(FlightNotFoundException e) {
		return e.getMessage();
	}
	
	//Handler for Invalid Flight Exception
	@ExceptionHandler(InvalidFlightException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	String flightInvalidHandler(InvalidFlightException e) {
		return e.getMessage();
	}
	
	//Handler for Booking Not Found Exception
	@ExceptionHandler(BookingNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String bookingNotFoundHandler(BookingNotFoundException e) {
		return e.getMessage();
	}
	
	//Handler for Invalid Booking Exception
	@ExceptionHandler(InvalidBookingException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	String bookingInvalidHandler(InvalidBookingException e) {
		return e.getMessage();
	}
	
	//Handler for Schedule Not Found Exception
	@ExceptionHandler(ScheduleNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String scheduleNotFoundHandler(ScheduleNotFoundException e) {
		return e.getMessage();
	}
		
	//Handler for Invalid Booking Exception
	@ExceptionHandler(InvalidScheduleException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	String scheduleInvalidHandler(InvalidScheduleException e) {
		return e.getMessage();
	}
	
	//Handler for Passenger Not Found Exception
	@ExceptionHandler(PassengerNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String passengerNotFoundHandler(PassengerNotFoundException e) {
		return e.getMessage();
	}
			
	//Handler for Invalid Passenger Exception
	@ExceptionHandler(InvalidPassengerException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	String passengerInvalidHandler(InvalidPassengerException e) {
		return e.getMessage();
	}
	
	//Handler for all other exceptions
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	String exceptionHandler(Exception e) {
		return e.getMessage();
	}
	
}
