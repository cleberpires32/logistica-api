package com.cleber.logistica.commo;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.cleber.logistica.api.imputDTO.EntregaInput;
import com.cleber.logistica.api.modelDTO.EntregaDTO;
import com.cleber.logistica.domain.model.Entrega;

import lombok.AllArgsConstructor;

/**
 * Classe criada para deixar o cogido mais independente do modelmapper caso um
 * dia fique depreciada
 */
@AllArgsConstructor
@Component
public class ModelMapperAssenble {

	private ModelMapper modelMapper;

	public EntregaDTO toModel(Entrega entrega) {
		return modelMapper.map(entrega, EntregaDTO.class);
	}

	public List<EntregaDTO> toCollectionModel(List<Entrega> listaEntraga) {
		return listaEntraga.stream()
				.map(this::toModel)
				.collect(Collectors.toList());
	}
	
	public Entrega toEntity(EntregaInput entregaInput) {
		return modelMapper.map(entregaInput, Entrega.class);
	}
}
