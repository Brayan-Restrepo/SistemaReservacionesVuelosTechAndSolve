package com.techandsolve.model.dto;

import java.io.Serializable;
import java.util.Date;

public class ReservaDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Date fechaReserva;
	private PersonaDto persona;
	private VueloDto vuelo;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getFechaReserva() {
		return fechaReserva;
	}
	public void setFechaReserva(Date fechaReserva) {
		this.fechaReserva = fechaReserva;
	}
	public PersonaDto getPersona() {
		return persona;
	}
	public void setPersona(PersonaDto persona) {
		this.persona = persona;
	}
	public VueloDto getVuelo() {
		return vuelo;
	}
	public void setVuelo(VueloDto vuelo) {
		this.vuelo = vuelo;
	}
		
}

