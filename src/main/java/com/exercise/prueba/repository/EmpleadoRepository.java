package com.exercise.prueba.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.exercise.prueba.model.Empleado;

@Repository
public interface EmpleadoRepository extends CrudRepository<Empleado, Integer> {

}
