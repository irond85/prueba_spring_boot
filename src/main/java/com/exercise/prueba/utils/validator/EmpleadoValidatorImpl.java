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

	private Date fechaActualDate = new Date();
	private LocalDate fechaActual = fechaActualDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

	@Override
	public void validarEmpleado(Empleado empleado) throws ApiUnprocessableEntity {

		Date fechaNacDate = empleado.getFecha_nacimiento();
		LocalDate fechaNacLocalDate = fechaNacDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		String fechaNacimiento = fechaNacLocalDate.toString();

		Date fechaVincDate = empleado.getFecha_vinculacion();
		LocalDate fechaVincLocalDate = fechaVincDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		String fechaVinculacion = fechaVincLocalDate.toString();

		if (empleado.getNombres() == null || empleado.getNombres().isEmpty()) {
			this.message("Ingresar el nombre es obligatorio");
		}

		if (empleado.getApellidos() == null || empleado.getApellidos().isEmpty()) {
			this.message("Ingresar el apellido es obligatorio");
		}

		if (empleado.getTipo_documento() == null || empleado.getTipo_documento().isEmpty()) {
			this.message("Ingresar el tipo de documento es obligatorio");
		}

		if (empleado.getNum_documento() == null || empleado.getNum_documento().isEmpty()) {
			this.message("Ingresar el numero de documento es obligatorio");
		}

		if (fechaNacimiento == null || fechaNacimiento.isEmpty()) {
			this.message("Ingresar la fecha de nacimiento es obligatoria");
		}

		if (fechaVinculacion == null || fechaVinculacion.isEmpty()) {
			this.message("La fecha de vinculación es obligatoria");
		}

		if (!isFechaValida(fechaNacimiento) || !isFechaValida(fechaVinculacion)) {
			this.message("El formato de la fecha es incorrecto");
		}

		if (empleado.getCargo() == null || empleado.getCargo().isEmpty()) {
			this.message("Ingresar el cargo es obligatorio");
		}

		if (empleado.getSalario() == null || empleado.getSalario().isNaN()) {
			this.message("Ingresar el salario es obligatorio");
		}

		if (!validarEdadEmpleado(empleado)) {
			message("El usuario es menor de edad");
		}

	}

	public boolean validarEdadEmpleado(Empleado empleado) {
		Date fechaNacDate = empleado.getFecha_nacimiento();
		LocalDate fechaNac = fechaNacDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		Integer years = Period.between(fechaNac, fechaActual).getYears();
		if (years >= 18) {
			return true;

		} else {
			return false;
		}
	}
	
	private Boolean isFechaValida(String fecha) {
		try {
			SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
			formatoFecha.setLenient(false);
			formatoFecha.parse(fecha);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}

	private void message(String message) throws ApiUnprocessableEntity {
		throw new ApiUnprocessableEntity(message);
	}

}
