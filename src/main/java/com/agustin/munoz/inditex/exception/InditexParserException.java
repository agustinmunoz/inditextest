package com.agustin.munoz.inditex.exception;

import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;

import com.agustin.munoz.inditex.error.InditexErrorCodes;
import com.agustin.munoz.inditex.error.InditexErrorMessages;

public class InditexParserException extends InditexException {
	
	
	private static final long serialVersionUID = 1L;
			
	public InditexParserException(@Nullable Throwable cause) {
		super(InditexErrorMessages.PARSER_EXCEPTION.getDescription(),InditexErrorMessages.PARSER_EXCEPTION.getCode(),HttpStatus.valueOf(InditexErrorCodes.ERROR_500.getCode()));
		
	}
	
	
	public InditexParserException() {
		
		this(null);
		
	}

}
