package com.cleber.logistica.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cleber.logistica.api.imputDTO.EntregaInput;
import com.cleber.logistica.api.modelDTO.EntregaDTO;
import com.cleber.logistica.commo.ModelMapperAssenble;
import com.cleber.logistica.domain.model.Entrega;
import com.cleber.logistica.domain.repository.EntregaRepository;
import com.cleber.logistica.domain.service.EntregaService;
import com.cleber.logistica.domain.service.FinalizacaoEntregaService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas")
public class EntregaController {

	private EntregaService entregaService;
	private EntregaRepository entregaRepository;
	private ModelMapperAssenble modelMapperAssenble;
	private FinalizacaoEntregaService finalizacaoEntregaService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EntregaDTO solicita(@Valid @RequestBody EntregaInput entregaInput) {
		Entrega entrega = modelMapperAssenble.toEntity(entregaInput);
		return modelMapperAssenble.toModel(entregaService.solicita(entrega));
	}

	@GetMapping
	public List<EntregaDTO> listar() {
		return modelMapperAssenble.toCollectionModel(entregaRepository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<EntregaDTO> porId(@PathVariable Long id) {
		return entregaRepository.findById(id).map(response -> 
		ResponseEntity.ok(modelMapperAssenble.toModel(response)))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PutMapping("/{entregaId}/finalizacao")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void finalizacaoEntrega(@PathVariable Long entregaId) {
		finalizacaoEntregaService.finalizar(entregaId);
	}

}
