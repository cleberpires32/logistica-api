package com.cleber.logistica.commo;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.cleber.logistica.api.modelDTO.OcorrenciaDTO;
import com.cleber.logistica.domain.model.Ocorrencia;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ModelMapperOcorrenciaAssemble {

	private ModelMapper modelMapper;
	
	public OcorrenciaDTO toModel(Ocorrencia ocorrencia) {
		
		return modelMapper.map(ocorrencia, OcorrenciaDTO.class);
	}
	
	public List<OcorrenciaDTO> toCollectionModelList(List<Ocorrencia> ocorrencias){
		return ocorrencias.stream().map(this::toModel).collect(Collectors.toList());
	}
}
