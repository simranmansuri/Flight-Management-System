package com.cg.exception;

public class InvalidScheduleException extends RuntimeException {

	public InvalidScheduleException() {
		
	}
	
	public InvalidScheduleException(String message) {
		super(message);
	}

}
