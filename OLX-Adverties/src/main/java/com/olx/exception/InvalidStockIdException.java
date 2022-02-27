package com.olx.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidStockIdException extends RuntimeException {

	
	private String message;
	
	public InvalidStockIdException() {
		this.message = " ";
	}
	
	public InvalidStockIdException(String message) {
		super();
		this.message = message;
	}

	@Override
	public String toString() {
		return "InvalidStockIdException [message=" + message + "]";
	}
	
	
}
