package com.aravind.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MyException {
	
	@ExceptionHandler(value=InvalidUserDataException.class)
	public ResponseEntity<AppError> invalidUserDataException(InvalidUserDataException exception){
		AppError error=new AppError();
		error.setErrorCode("REGAPP101");
		error.setErrorMsg(exception.getMessage());
		return new ResponseEntity<AppError>(error,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
