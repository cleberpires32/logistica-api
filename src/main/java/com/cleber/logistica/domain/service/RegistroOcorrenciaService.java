package com.cleber.logistica.domain.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.cleber.logistica.domain.model.Entrega;
import com.cleber.logistica.domain.model.Ocorrencia;

import lombok.AllArgsConstructor;

/** Não precisa do save por que o jakarta persiste ja entende que existe um alteração em Entraga e faz a persistencia em cascata pela transação em ocorrencia */
@AllArgsConstructor
@Service
public class RegistroOcorrenciaService {

	private BuscarEntragaService entregaService;
	
	@Transactional
	public Ocorrencia registrar(Long id, String descricao) {
		Entrega entrega = entregaService.buscarEntrega(id);	
		return  entrega.adicionarOcorrencia(descricao);
	}
}
