package com.exercise.prueba.utils.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.exercise.prueba.model.Empleado;
import com.exercise.prueba.utils.exceptions.ApiUnprocessableEntity;

@Component
public class EmpleadoValidatorImpl implements EmpleadoValidator {

	private LocalDate fechaActual = LocalDate.now();

	@Override
	public void validarEmpleado(Empleado empleado) throws ApiUnprocessableEntity {

		LocalDate fechaNacDate = empleado.getFechaNacimiento();

		LocalDate fechaVincDate = empleado.getFechaVinculacion();

		if (empleado.getNombres() == null || empleado.getNombres().isEmpty()) {
			this.message("El nombre es obligatorio");
		}

		if (empleado.getApellidos() == null || empleado.getApellidos().isEmpty()) {
			this.message("El apellido es obligatorio");
		}

		if (empleado.getTipoDocumento() == null || empleado.getTipoDocumento().isEmpty()) {
			this.message("El tipo de documento es obligatorio");
		}

		if (empleado.getNumDocumento() == null || empleado.getNumDocumento().isEmpty()) {
			this.message("El numero de documento es obligatorio");
		}

		if (empleado.getFechaNacimiento() == null || empleado.getFechaNacimiento().equals("")) {
			this.message("La fecha de nacimiento es obligatoria");
		}

		if (empleado.getFechaVinculacion() == null || empleado.getFechaVinculacion().equals("")) {
			this.message("La fecha de vinculación es obligatoria");
		}

		/*if (!isFechaValida(fechaNacimiento) || !isFechaValida(fechaVinculacion)) {
			this.message("El formato de la fecha es incorrecto");
		}*/

		if (empleado.getFechaNacimiento().isAfter(empleado.getFechaVinculacion())) {
			this.message("La fecha de nacimiento debe ser anterior a la fecha de vinculación");
		}

		if (empleado.getCargo() == null || empleado.getCargo().isEmpty()) {
			this.message("El cargo es obligatorio");
		}

		if (empleado.getSalario() == null || empleado.getSalario().isNaN()) {
			this.message("El salario es obligatorio");
		}

		if (!validarEdadEmpleado(empleado)) {
			this.message("El usuario es menor de edad");
		}

	}

	@Override
	public boolean validarEdadEmpleado(Empleado empleado) {
		LocalDate fechaNacDate = empleado.getFechaNacimiento();

		Integer years = Period.between(fechaNacDate, fechaActual).getYears();
		if (years >= 18) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void message(String message) throws ApiUnprocessableEntity {
		throw new ApiUnprocessableEntity(message);
	}

}
