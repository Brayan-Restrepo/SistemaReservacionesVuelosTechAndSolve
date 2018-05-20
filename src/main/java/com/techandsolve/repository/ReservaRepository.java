package com.techandsolve.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.techandsolve.model.entity.Reserva;

@Repository
public interface ReservaRepository extends CrudRepository<Reserva, Integer> {

	@Query(value = "SELECT r FROM Reserva r JOIN r.persona p WHERE p.cedula = :cedula ")
	public List<Reserva> findByCedula(@Param("cedula") String cedula);
	
	@Query(value = "SELECT r FROM Reserva r JOIN r.persona p WHERE p.cedula = :cedula AND r.fechaReserva = :fecha_reserva ")
	public List<Reserva> findByCedulaFechaReserva(@Param("cedula") String cedula, @Param("fecha_reserva") Date fechaReserva);
}
