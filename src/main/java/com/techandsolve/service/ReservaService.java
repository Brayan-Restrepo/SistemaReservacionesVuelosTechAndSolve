package com.techandsolve.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.techandsolve.model.dto.ReservaDto;

public interface ReservaService {

	public Map<String, String> reservarVuelo(ReservaDto reservaDto);
	
	public List<ReservaDto> findByCedula(String cedula);
	
	public List<ReservaDto> findByCedulaFechaReserva(String cedula, Date fechaReserva);
}
