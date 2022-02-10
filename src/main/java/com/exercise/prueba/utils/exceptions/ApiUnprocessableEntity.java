package com.exercise.prueba.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class ApiUnprocessableEntity extends Exception {
	
	/**
	 * 422
	 */
	private static final long serialVersionUID = -2193295433377883347L;

	public ApiUnprocessableEntity(String message) {
		super(message);
	}

}
