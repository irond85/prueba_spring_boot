package com.exercise.prueba.utils.validator;

import org.springframework.stereotype.Service;

import com.exercise.prueba.model.Empleado;
import com.exercise.prueba.utils.exceptions.ApiUnprocessableEntity;

@Service
public interface EmpleadoValidator {
	
	void validarEmpleado(Empleado empleado) throws ApiUnprocessableEntity;
	void message(String mensaje) throws ApiUnprocessableEntity;

}
