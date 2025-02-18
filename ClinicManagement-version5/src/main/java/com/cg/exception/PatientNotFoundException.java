package com.cg.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PatientNotFoundException extends Exception {

	public PatientNotFoundException() {
		super();
	}
	public PatientNotFoundException(String message) {
		super(message);
	}
	

}
