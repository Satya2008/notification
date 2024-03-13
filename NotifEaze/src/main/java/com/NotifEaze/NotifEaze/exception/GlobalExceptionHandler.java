package com.NotifEaze.NotifEaze.exception;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(NotFoundExceptions.class)
	public ResponseEntity<MyErrorDetails> handleUserException(NotFoundExceptions ex, WebRequest wr){
		MyErrorDetails myErrorDetails = new MyErrorDetails();
		myErrorDetails.setTimeStamp(LocalDate.now());
		myErrorDetails.setMessage(ex.getMessage());
		myErrorDetails.setDiscription(wr.getDescription(false));
		return new ResponseEntity<MyErrorDetails>(myErrorDetails, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(SomethingWentWrong.class)
	public ResponseEntity<MyErrorDetails> handleUserException(SomethingWentWrong ex, WebRequest wr){
		MyErrorDetails myErrorDetails = new MyErrorDetails();
		myErrorDetails.setTimeStamp(LocalDate.now());
		myErrorDetails.setMessage(ex.getMessage());
		myErrorDetails.setDiscription(wr.getDescription(false));
		return new ResponseEntity<MyErrorDetails>(myErrorDetails, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<MyErrorDetails> Exception(Exception ex, WebRequest wr){
		MyErrorDetails myErrorDetails = new MyErrorDetails();
		myErrorDetails.setTimeStamp(LocalDate.now());
		myErrorDetails.setMessage(ex.getMessage());
		myErrorDetails.setDiscription(wr.getDescription(false));
		return new ResponseEntity<MyErrorDetails>(myErrorDetails, HttpStatus.BAD_REQUEST);
	}
}
