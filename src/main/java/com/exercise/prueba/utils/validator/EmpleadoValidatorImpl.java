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

		LocalDate fechaNacDate = empleado.getFechaNacimiento();
		//LocalDate fechaNacLocalDate = fechaNacDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		String fechaNacimiento = fechaNacDate.toString();

		LocalDate fechaVincDate = empleado.getFechaVinculacion();
		//LocalDate fechaVincLocalDate = fechaVincDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		String fechaVinculacion = fechaVincDate.toString();

		if (empleado.getNombres() == null || empleado.getNombres().isEmpty()) {
			this.message("Ingresar el nombre es obligatorio");
		}

		if (empleado.getApellidos() == null || empleado.getApellidos().isEmpty()) {
			this.message("Ingresar el apellido es obligatorio");
		}

		if (empleado.getTipoDocumento() == null || empleado.getTipoDocumento().isEmpty()) {
			this.message("Ingresar el tipo de documento es obligatorio");
		}

		if (empleado.getNumDocumento() == null || empleado.getNumDocumento().isEmpty()) {
			this.message("Ingresar el numero de documento es obligatorio");
		}

		if (empleado.getFechaNacimiento() == null) {
			this.message("Ingresar la fecha de nacimiento es obligatoria");
		}

		if (fechaVinculacion == null || fechaVinculacion.isEmpty()) {
			this.message("La fecha de vinculación es obligatoria");
		}

		if (!isFechaValida(fechaNacimiento) || !isFechaValida(fechaVinculacion)) {
			this.message("El formato de la fecha es incorrecto");
		}

		if (empleado.getFechaNacimiento().isAfter(empleado.getFechaVinculacion())) {
			this.message("La fecha de nacimiento debe ser anterior a la fecha de vinculación");
		}

		if (empleado.getCargo() == null || empleado.getCargo().isEmpty()) {
			this.message("Ingresar el cargo es obligatorio");
		}

		if (empleado.getSalario() == null || empleado.getSalario().isNaN()) {
			this.message("Ingresar el salario es obligatorio");
		}

		if (!edadEmpleado(empleado)) {
			this.message("El usuario es menor de edad");
		}

	}

	public boolean edadEmpleado(Empleado empleado) {
		LocalDate fechaNacDate = empleado.getFechaNacimiento();

		Integer years = Period.between(fechaNacDate, fechaActual).getYears();
		if (years >= 18) {
			return true;
		} else {
			return false;
		}
	}

	private Boolean isFechaValida(String fecha) {
		LocalDate localDate = LocalDate.parse(fecha);

		int day = localDate.getDayOfMonth();
		int month =  localDate.getMonthValue();

		if (month <= 12) {
			if (day > 31) {
				return false;
			}
			return true;
		}

		System.out.println(day + " " + month);

		return true;
	}

	@Override
	public void message(String message) throws ApiUnprocessableEntity {
		throw new ApiUnprocessableEntity(message);
	}

}
