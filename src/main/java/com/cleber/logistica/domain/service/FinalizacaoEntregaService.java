package com.cleber.logistica.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cleber.logistica.domain.model.Entrega;
import com.cleber.logistica.domain.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class FinalizacaoEntregaService {

	private BuscarEntragaService buscarEntragaService;
	private EntregaRepository entregaRepository;
	
	@Transactional
	public void finalizar(Long entregaId) {
		
		Entrega entrega = buscarEntragaService.buscarEntrega(entregaId);
		entrega.finalizar();
		entregaRepository.save(entrega);
	}
}
