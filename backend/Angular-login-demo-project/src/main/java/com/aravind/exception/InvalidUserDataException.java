package com.aravind.exception;

@SuppressWarnings("serial")
public class InvalidUserDataException extends RuntimeException{

	public InvalidUserDataException() {
		super();
	}
	
	public InvalidUserDataException(String msg) {
		super(msg);
	}

}
