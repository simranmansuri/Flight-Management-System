package com.cg.exception;

public class PassengerNotFoundException extends RuntimeException {

	public PassengerNotFoundException() {
		
	}

	public PassengerNotFoundException(String message) {
		super(message);
		
	}

}
