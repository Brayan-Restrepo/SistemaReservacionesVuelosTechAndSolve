package com.techandsolve.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techandsolve.model.dto.ReservaDto;
import com.techandsolve.model.entity.Persona;
import com.techandsolve.model.entity.Reserva;
import com.techandsolve.model.entity.Vuelo;
import com.techandsolve.repository.PersonaRepository;
import com.techandsolve.repository.ReservaRepository;
import com.techandsolve.repository.VueloRepository;
import com.techandsolve.service.ReservaService;

@Service
public class ReservaServiceImpl implements ReservaService{

	@Autowired 
	PersonaRepository personaRepository;
	@Autowired 
	ReservaRepository reservaRepository;
	@Autowired 
	VueloRepository vueloRepository;
	
	ModelMapper modelMapper = new ModelMapper();

	@Override
	public Map<String, String> reservarVuelo(ReservaDto reservaDto) {
		Map<String, String> response = new Hashtable<String, String>();
		
		if(Objects.nonNull(reservaDto)){
			Reserva reserva = modelMapper.map(reservaDto, Reserva.class);
			
			if(Objects.nonNull(reserva.getPersona())){
				
				if(this.calularEdad(reserva.getPersona().getFechaNacimiento()) < 18) {
					response.put("status", "Error");
					response.put("mensaje", "Es menos de edad, no puede reservar");
					return response;
				}				
				
				if(Objects.nonNull(reserva.getVuelo())){
					Vuelo vuelo = vueloRepository.findOne(reserva.getVuelo().getId());
					if(!Objects.nonNull(vuelo)) {
						response.put("status", "Error");
						response.put("mensaje", "Vuelo no existe");
						return response;
					}
				}else{
					response.put("status", "Error");
					response.put("mensaje", "Datos Vuelo no valido");
					return response;
				}				
												
				Persona persona = personaRepository.findByCedula(reserva.getPersona().getCedula());
								
				if(Objects.nonNull(persona)) {
					
					int numeroReservaDia = this.findByCedulaFechaReserva(reserva.getPersona().getCedula(), reserva.getFechaReserva()).size();
					if(numeroReservaDia != 0) {
						response.put("status", "Error");
						response.put("mensaje", "Ha superado el numero de reservas por dia");
						return response;						
					}
					
					persona.setApellido(reserva.getPersona().getApellido());
					persona.setFechaNacimiento(reserva.getPersona().getFechaNacimiento());
					persona.setNombre(reserva.getPersona().getNombre());
					persona.setTelefono(reserva.getPersona().getTelefono());					
					personaRepository.save(persona);
					
					reserva.setPersona(persona);
					reservaRepository.save(reserva);
					response.put("status", "Ok");
					response.put("mensaje", "Se creo la reserva");
					return response;
				}else {
					personaRepository.save(reserva.getPersona());
					reservaRepository.save(reserva);
					response.put("status", "Ok");
					response.put("mensaje", "Se creo la persona y la reserva");
					return response;
				}
			}else {
				response.put("status", "Error");
				response.put("mensaje", "Datos Persona no valido");
				return response;
			}
		}else {
			response.put("status", "Error");
			response.put("mensaje", "Datos Recerva no valido");
			return response;
		}
	}

	@Override
	public List<ReservaDto> findByCedula(String cedula) {				
		return this.convertToListDto(reservaRepository.findByCedula(cedula));
	}

	@Override
	public List<ReservaDto> findByCedulaFechaReserva(String cedula, Date fechaReserva){
		return this.convertToListDto(reservaRepository.findByCedulaFechaReserva(cedula, fechaReserva));
	}
	
	@SuppressWarnings("unused")
	private List<ReservaDto> convertToListDto(List<Reserva> reservas){
		return reservas.stream().map(reserva -> modelMapper.map(reserva, ReservaDto.class)).collect(Collectors.toList());
	}
	
	@SuppressWarnings("unused")
	private int calularEdad(Date fecha) {
		 LocalDate hoy = LocalDate.now();   
		 LocalDate nacimiento = fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); 
		 int edad = (int) ChronoUnit.YEARS.between(nacimiento, hoy);
		 return edad;
	}
}
