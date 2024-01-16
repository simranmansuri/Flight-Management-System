package com.cg.exception;

public class InvalidBookingException extends RuntimeException {

	public InvalidBookingException() {
		
	}

	public InvalidBookingException(String message) {
		super(message);
		
	}

	
}
