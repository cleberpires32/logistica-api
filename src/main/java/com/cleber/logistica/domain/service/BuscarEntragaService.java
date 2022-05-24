package com.cleber.logistica.domain.service;

import org.springframework.stereotype.Service;

import com.cleber.logistica.domain.exception.EntitdadeNaoEncontradaException;
import com.cleber.logistica.domain.model.Entrega;
import com.cleber.logistica.domain.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BuscarEntragaService {

	private EntregaRepository entregaRepository;
	
	public Entrega buscarEntrega(Long id) {
		Entrega entrega = entregaRepository
				.findById(id).orElseThrow(()-> new EntitdadeNaoEncontradaException("Entrega não encontrada"));	
	return entrega;
	}
}
