package com.agustin.munoz.inditex.exception;

import org.springframework.dao.DataAccessException;

public class InditexDataAccessException extends DataAccessException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InditexDataAccessException(String msg) {
		super(msg);
	}

	
	
 
}
