package com.cg.exception;

import java.util.*;

public class ErrorDetails {
	private Date timestamp;
	private String message;
	private String details;
	public ErrorDetails(Date timestamp, String message, String details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}
	public ErrorDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public String getMessage() {
		return message;
	}
	public String getDetails() {
		return details;
	}

	
}
