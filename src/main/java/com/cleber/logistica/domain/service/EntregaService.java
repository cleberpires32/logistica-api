package com.cleber.logistica.domain.service;

import java.time.OffsetDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cleber.logistica.domain.model.Cliente;
import com.cleber.logistica.domain.model.Entrega;
import com.cleber.logistica.domain.model.StatusEntrega;
import com.cleber.logistica.domain.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class EntregaService {

	
	private EntregaRepository entregaRepository;
	private ClienteService clienteService;
	
	@Transactional
	public Entrega solicita(Entrega entrega) {
		Cliente cliente = clienteService.buscar(entrega.getCliente().getId());
		entrega.setCliente(cliente);
		entrega.setStatusEntrega(StatusEntrega.PENDENTE);
		entrega.setDataPedido(OffsetDateTime.now());
		return entregaRepository.save(entrega);
	}

}
