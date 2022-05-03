package com.exercise.prueba.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@Entity
@Table(name= "empleado", schema = "empresa_db")
public class Empleado implements Serializable {

	private static final long serialVersionUID = -2783956734929565314L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_empleado", nullable = true)
	private Integer idEmpleado;
	
	private String nombres;

	private String apellidos;

	@Column(name = "tipo_documento", nullable = true)
	private String tipoDocumento;

	@Column(name = "num_documento", nullable = true)
	private String numDocumento;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-M-dd")
	@Column(name = "fecha_nacimiento", columnDefinition = "DATE", nullable = true)
	private LocalDate fechaNacimiento;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@Column(name = "fecha_vinculacion", columnDefinition = "DATE", nullable = true)
	private LocalDate fechaVinculacion;

	private String cargo;

	private Double salario;
	
}
