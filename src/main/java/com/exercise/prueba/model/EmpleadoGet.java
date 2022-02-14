package com.exercise.prueba.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class EmpleadoGet {
	private Integer id;

	private String nombres;

	private String apellidos;

	private String tipo_documento;

	private String num_documento;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date fecha_nacimiento;

	private String edadActual;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date fecha_vinculacion;

	private String tiempoVinculacion;

	private String cargo;

	private Double salario;

}
