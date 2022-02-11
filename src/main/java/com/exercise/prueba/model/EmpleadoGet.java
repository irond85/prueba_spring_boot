package com.exercise.prueba.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class EmpleadoGet {
	private Integer id;

	private String nombres;

	private String apellidos;

	private String tipo_documento;

	private String num_documento;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	// @JsonFormat(pattern = "yyyy-MM-dd")
	private Date fecha_nacimiento;

	private String edadActual;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	// @JsonFormat(pattern = "yyyy-MM-dd")
	private Date fecha_vinculacion;

	private String tiempoVinculacion;

	private String cargo;

	private Double salario;

}
