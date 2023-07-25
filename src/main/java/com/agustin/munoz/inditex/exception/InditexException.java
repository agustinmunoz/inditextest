package com.agustin.munoz.inditex.exception;


import org.springframework.http.HttpStatus;





public class InditexException extends RuntimeException {

	
	private static final long serialVersionUID = 1L;
	
	private  HttpStatus status;
	private String inditexCode;
	

	public InditexException(String msg) {
		super(msg);
	}


	public InditexException(String msg, String inditexCode, HttpStatus httpStatus) {
		super(msg);
		this.status= httpStatus;
		this.inditexCode=inditexCode;
		
	}
	
	


	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}



	public String getInditexCode() {
		return inditexCode;
	}

	public void setInditexCode(String inditexCode) {
		this.inditexCode = inditexCode;
	}


	
	
 
}
