package com.techandsolve.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techandsolve.model.dto.VueloDto;
import com.techandsolve.service.VueloService;
import com.techandsolve.util.ConstantesUtil;

@RestController
@RequestMapping(ConstantesUtil.URL_API)
public class VueloController {
	
	@Autowired 
	private VueloService vueloService;
	
	@RequestMapping(value = ConstantesUtil.CONSULTAR_VUELOS, method = RequestMethod.GET)
	public List<VueloDto> consultarVuelos() {
		List<VueloDto> vuelos = vueloService.findAll();
		return vuelos;
	}
}
