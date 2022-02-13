package com.exercise.prueba.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exercise.prueba.model.Empleado;
import com.exercise.prueba.model.EmpleadoGet;
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
	public ResponseEntity<Object> guardarEmpleado(@RequestBody Empleado empleado) throws ApiUnprocessableEntity {
		this.empleadoValidator.validarEmpleado(empleado);
		empleadoService.guardarEmpleado(empleado);

		return ResponseEntity.ok(Boolean.TRUE);
	}
	
	@GetMapping(produces = "application/json")
	public List<EmpleadoGet> listarEmpleados() {
		return empleadoService.obtenerEmpleados();
	}

		@GetMapping(value = "/{id}")
	public String eliminarEmpleado(@PathVariable Integer id) {
		return empleadoService.eliminarEmpleadoById(id);
	}
}
