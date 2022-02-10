package com.exercise.prueba.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ApiNotFound extends Exception {

	/**
	 * 404
	 */
	private static final long serialVersionUID = 5883327349168248268L;	
	
	public ApiNotFound(String message) {
		super(message);
	}

}
