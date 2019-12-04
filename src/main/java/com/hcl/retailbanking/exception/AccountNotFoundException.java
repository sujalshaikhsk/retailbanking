package com.hcl.retailbanking.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AccountNotFoundException extends RuntimeException{
	
    Logger logger = LoggerFactory.getLogger(AccountNotFoundException.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public void printStackTrace() {
		// TODO Auto-generated method stub
		super.printStackTrace();
		logger.error("The Account is not Found");
	}
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "The Account is not Found";
	}

}
