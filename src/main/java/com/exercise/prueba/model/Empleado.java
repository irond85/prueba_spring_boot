package com.exercise.prueba.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
public class Empleado implements Serializable {

	private static final long serialVersionUID = -2783956734929565314L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String nombres;

	private String apellidos;

	private String tipo_documento;

	private String num_documento;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	// @JsonFormat(pattern = "yyyy-MM-dd")
	private Date fecha_nacimiento;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	//@JsonFormat(pattern = "yyyy-MM-dd")
	private Date fecha_vinculacion;

	private String cargo;

	private Double salario;

}
