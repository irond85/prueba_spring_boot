package com.exercise.prueba.service;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.cliftonlabs.json_simple.JsonObject;
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

	public JsonObject obtenerEmpleado(Empleado empleado) {
		Empleado res = empleadoRepository.findById(empleado.getIdEmpleado()).get();
		return crearJson(res);
	}
	
	public String calcularTiempoVinculado(Empleado empleado) {
		LocalDate fechaVincDate = empleado.getFechaVinculacion();

		Period tiempoVinculacion = Period.between(fechaVincDate, fechaActual);
		return (String.format("Tiempo vinculado: %d Años, %d Meses y %d Días", tiempoVinculacion.getYears(),
				tiempoVinculacion.getMonths(), tiempoVinculacion.getDays()));
	}

	public String calcularEdadActual(Empleado empleado) {
		LocalDate fechaNacDate = empleado.getFechaNacimiento();

		Period edadActual = Period.between(fechaNacDate, fechaActual);
		return (String.format("Edad del empleado: %d Años, %d Meses y %d Días", edadActual.getYears(),
				edadActual.getMonths(), edadActual.getDays()));
	}


	public List<JsonObject> obtenerEmpleados() {
		List<Empleado> resultado = new ArrayList<Empleado>();
		empleadoRepository.findAll().iterator().forEachRemaining(resultado::add);

		List<JsonObject> empleados = new ArrayList<JsonObject>();

		for (Empleado empleado : resultado) {
			JsonObject jsonEmp = new JsonObject();
			jsonEmp = crearJson(empleado);
			empleados.add(jsonEmp);
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

	@JsonPropertyOrder({ "idEmpleado", "nombres", "apellidos", "tipoDocumento", "numDocumento", "fechaNacimiento", "edad",
	"fechaVinculacion", "tiempoVinculado", "cargo", "salario" })
	private JsonObject crearJson(Empleado empleado) {
		JsonObject jsonEmpl = new JsonObject();
		jsonEmpl.put("idEmpleado", empleado.getIdEmpleado());
		jsonEmpl.put("nombres", empleado.getNombres());
		jsonEmpl.put("apellidos", empleado.getApellidos());
		jsonEmpl.put("tipoDocumento", empleado.getTipoDocumento());
		jsonEmpl.put("numDocumento", empleado.getNumDocumento());
		jsonEmpl.put("fechaNacimiento", empleado.getFechaNacimiento());
		jsonEmpl.put("edad", calcularEdadActual(empleado));
		jsonEmpl.put("fechaVinculacion", empleado.getFechaVinculacion());
		jsonEmpl.put("tiempoVinculado", calcularTiempoVinculado(empleado));
		jsonEmpl.put("cargo", empleado.getCargo());
		jsonEmpl.put("salario", empleado.getSalario());

		return jsonEmpl;
	}

}
