package com.cg.exception;

public class InvalidUserException extends RuntimeException {

	public InvalidUserException() {
		
	}

	public InvalidUserException(String message) {
		super(message);
		
	}

}
