package com.exercise.prueba.service;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exercise.prueba.model.Empleado;
import com.exercise.prueba.repository.EmpleadoRepository;

@Service
public class EmpleadoService {

	@Autowired
	private EmpleadoRepository empleadoRepository;
	private Date fechaActualDate = new Date();
	private LocalDate fechaActual = fechaActualDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

	public String calcularTiempoVinculacion(Empleado empleado) {
		Date fechaVincDate = empleado.getFecha_vinculacion();
		LocalDate fechaVinc = fechaVincDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		Period tiempoVinculacion = Period.between(fechaVinc, fechaActual);
		return (String.format("Tiempo vinculado: %d Años y %d Meses", tiempoVinculacion.getYears(),
				tiempoVinculacion.getMonths()));
	}

	public void guardarEmpleado(Empleado empleado) {

		empleadoRepository.save(empleado);
	}

	public List<Empleado> obtenerEmpleados() {
		List<Empleado> resultado = new ArrayList<Empleado>();
		empleadoRepository.findAll().iterator().forEachRemaining(resultado::add);

		return resultado;
	}

}
