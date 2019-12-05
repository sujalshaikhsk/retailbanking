package com.hcl.retailbanking.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(CommonException.class)
	public ResponseEntity<ErrorResponse> commonException(CommonException e) {
		return ResponseEntity.badRequest().body(new ErrorResponse(e.ex.getCode(), e.ex.getMessage()));

	}
}
