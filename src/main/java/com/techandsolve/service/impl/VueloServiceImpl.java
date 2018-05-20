package com.techandsolve.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.techandsolve.model.dto.VueloDto;
import com.techandsolve.model.entity.Vuelo;
import com.techandsolve.repository.VueloRepository;
import com.techandsolve.service.VueloService;

@Service
public class VueloServiceImpl implements VueloService{

	@Autowired 
	private VueloRepository vueloRepository;
	
	@Override
	public Page<Vuelo> findAll(Pageable pageable) {		
		return vueloRepository.findAll(pageable);
	}

	@Override
	public List<VueloDto> findAll() {

		ModelMapper modelMapper = new ModelMapper();
		List<Vuelo> vuelos = (List<Vuelo>) vueloRepository.findAll();
		
		return vuelos.stream().map(vuelo -> modelMapper.map(vuelo, VueloDto.class)).collect(Collectors.toList());
	}

}
