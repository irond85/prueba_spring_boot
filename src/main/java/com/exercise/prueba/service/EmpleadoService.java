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
import com.exercise.prueba.model.EmpleadoGet;
import com.exercise.prueba.repository.EmpleadoRepository;

@Service
public class EmpleadoService {

	@Autowired
	private EmpleadoRepository empleadoRepository;

	private Date fechaActualDate = new Date();
	private LocalDate fechaActual = fechaActualDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

	public void guardarEmpleado(Empleado empleado) {
		empleadoRepository.save(empleado);
	}
	
	public String calcularTiempoVinculacion(Empleado empleado) {
		Date fechaVincDate = empleado.getFecha_vinculacion();
		LocalDate fechaVinc = fechaVincDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		Period tiempoVinculacion = Period.between(fechaVinc, fechaActual);
		return (String.format("Tiempo vinculado: %d Años, %d Meses y %d Días", tiempoVinculacion.getYears(),
				tiempoVinculacion.getMonths(), tiempoVinculacion.getDays()));
	}

	public String calcularEdadActual(Empleado empleado) {
		Date fechaNacDate = empleado.getFecha_nacimiento();
		LocalDate fechaNac = fechaNacDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		Period edadActual = Period.between(fechaNac, fechaActual);
		return (String.format("Edad del empleado: %d Años, %d Meses y %d Días", edadActual.getYears(),
				edadActual.getMonths(), edadActual.getDays()));
	}


	public List<EmpleadoGet> obtenerEmpleados() {
		
		List<Empleado> resultado = new ArrayList<Empleado>();
		empleadoRepository.findAll().iterator().forEachRemaining(resultado::add);

		List<EmpleadoGet> empleados = new ArrayList<EmpleadoGet>();

		for (Empleado empleado : resultado) {
			EmpleadoGet empleadoGet = new EmpleadoGet();
			empleadoGet.setId(empleado.getId());
			empleadoGet.setNombres(empleado.getNombres());
			empleadoGet.setApellidos(empleado.getApellidos());
			empleadoGet.setTipo_documento(empleado.getTipo_documento());
			empleadoGet.setNum_documento(empleado.getNum_documento());
			empleadoGet.setFecha_nacimiento(empleado.getFecha_nacimiento());
			empleadoGet.setEdadActual(calcularEdadActual(empleado));
			empleadoGet.setFecha_vinculacion(empleado.getFecha_vinculacion());
			empleadoGet.setTiempoVinculacion(calcularTiempoVinculacion(empleado));
			empleadoGet.setCargo(empleado.getCargo());
			empleadoGet.setSalario(empleado.getSalario());

			empleados.add(empleadoGet);
		}

		return empleados;
	}
	
	public String eliminarEmpleadoById(Integer id) {
		if (empleadoRepository.findById(id).isPresent()) {
			empleadoRepository.deleteById(id);
			return "Empleado eliminado correctamente.";
		}
		return "Error! El empleado no existe";
	}

}
