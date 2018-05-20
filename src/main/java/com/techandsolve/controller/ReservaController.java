package com.techandsolve.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techandsolve.model.dto.ReservaDto;
import com.techandsolve.service.ReservaService;
import com.techandsolve.util.ConstantesUtil;

@RestController
@RequestMapping(ConstantesUtil.URL_API)
public class ReservaController {
	
	@Autowired 
	private ReservaService reservaService;
		
	@PostMapping(ConstantesUtil.RESERVAR)
	public Map<String, String> reservarVuelo(@RequestBody ReservaDto reserva) {
		return reservaService.reservarVuelo(reserva);
	}
	
	@GetMapping(ConstantesUtil.RESERVAR_CONSULTAR)
	public List<ReservaDto> findByCedula(@PathVariable String cedula) {
		return reservaService.findByCedula(cedula);
	}
		
}
