package com.exercise.prueba.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

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

	@JsonFormat(pattern = "yyyy-M-dd")
	private Date fecha_nacimiento;

	@JsonFormat(pattern = "yyyy-M-dd" )
	private Date fecha_vinculacion;

	private String cargo;

	private Double salario;
	
}
