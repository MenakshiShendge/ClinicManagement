package com.cg.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class DoctorNotFoundException extends Exception {

	public DoctorNotFoundException() {
		super();
	}
	public DoctorNotFoundException(String message) {
		super(message);
	}
	

}
