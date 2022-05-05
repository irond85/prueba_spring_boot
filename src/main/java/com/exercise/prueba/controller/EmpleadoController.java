package com.exercise.prueba.controller;

import java.util.List;

import com.github.cliftonlabs.json_simple.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exercise.prueba.model.Empleado;
import com.exercise.prueba.service.EmpleadoService;
import com.exercise.prueba.utils.exceptions.ApiUnprocessableEntity;
import com.exercise.prueba.utils.validator.EmpleadoValidatorImpl;

@RestController
@RequestMapping("api/empleado")
public class EmpleadoController {

	@Autowired
	private EmpleadoService empleadoService;

	@Autowired
	private EmpleadoValidatorImpl empleadoValidator;

	@PostMapping()
	public JsonObject guardarEmpleado(@RequestBody Empleado empleado) throws ApiUnprocessableEntity {
		this.empleadoValidator.validarEmpleado(empleado);
		empleadoService.guardarEmpleado(empleado);
		return empleadoService.obtenerEmpleado(empleado);
	}

	/*
	@GetMapping(produces = "application/json")
	public Empleado mostrarEmpleado(Empleado empleado) {
		return empleadoService.obtenerEmpleado(empleado);
	}
	 */


	@GetMapping(produces = "application/json")
	public List<JsonObject> listarEmpleados() {
		return empleadoService.obtenerEmpleados();
	}

	@GetMapping(value = "delete/{id}")
	public String eliminarEmpleado(@PathVariable Integer id) {
		return empleadoService.eliminarEmpleadoById(id);
	}

}
