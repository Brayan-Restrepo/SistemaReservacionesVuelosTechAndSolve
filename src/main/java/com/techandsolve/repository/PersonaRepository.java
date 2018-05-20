package com.techandsolve.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.techandsolve.model.entity.Persona;

@Repository
public interface PersonaRepository extends CrudRepository<Persona, Integer> {

	public Persona findByCedula(String cedula);
}
