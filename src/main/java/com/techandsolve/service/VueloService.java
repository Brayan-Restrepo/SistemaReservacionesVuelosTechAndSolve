package com.techandsolve.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.techandsolve.model.dto.VueloDto;
import com.techandsolve.model.entity.Vuelo;

public interface VueloService {

	public Page<Vuelo> findAll(Pageable pageable);
	public List<VueloDto> findAll();
	
}
