package com.hcl.retailbanking.exception;

import java.io.Serializable;

public class ErrorResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer statusCode;
	private String message;

	public ErrorResponse(Integer statusCode, String message) {
		this.statusCode = statusCode;
		this.message = message;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
