package com.cleber.logistica.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cleber.logistica.api.imputDTO.OcorrenciaInput;
import com.cleber.logistica.api.modelDTO.OcorrenciaDTO;
import com.cleber.logistica.commo.ModelMapperOcorrenciaAssemble;
import com.cleber.logistica.domain.model.Entrega;
import com.cleber.logistica.domain.model.Ocorrencia;
import com.cleber.logistica.domain.service.BuscarEntragaService;
import com.cleber.logistica.domain.service.RegistroOcorrenciaService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/entregas/{entregaId}/ocorrencias")
public class OcorrenciaController {
	
	private RegistroOcorrenciaService registroOcorrenciaService;
	private ModelMapperOcorrenciaAssemble ocorrenciaAssemble;
	private BuscarEntragaService buscarEntragaService;
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OcorrenciaDTO registrar(@PathVariable Long entregaId, @Valid @RequestBody OcorrenciaInput ocorrenciaInput) {
		
	Ocorrencia ocorrenciaRegistrada = registroOcorrenciaService.registrar(entregaId, ocorrenciaInput.getDescricao());
	return ocorrenciaAssemble.toModel(ocorrenciaRegistrada);
	}
	
	@GetMapping
	public List<OcorrenciaDTO> listaOcorrencias(@PathVariable Long entregaId){
		Entrega entrega = buscarEntragaService.buscarEntrega(entregaId);
		return ocorrenciaAssemble.toCollectionModelList(entrega.getOcorrencia());
	}
}
