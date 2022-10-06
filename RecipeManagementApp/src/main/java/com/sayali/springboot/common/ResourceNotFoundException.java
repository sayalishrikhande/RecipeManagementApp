package com.sayali.springboot.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResourceNotFoundException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LoggerFactory.getLogger(ResourceNotFoundException.class);
	
	public ResourceNotFoundException(final String message) {
		super(message);
		LOGGER.error(message);
	}
	
	public ResourceNotFoundException(final String message, final Throwable cause) {
		super(message, cause);
		LOGGER.error(message, cause);
	}
	

}
