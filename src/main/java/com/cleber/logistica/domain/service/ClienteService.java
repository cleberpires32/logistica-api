package com.cleber.logistica.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cleber.logistica.domain.exception.NegocioException;
import com.cleber.logistica.domain.model.Cliente;
import com.cleber.logistica.domain.repository.ClienteRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ClienteService {

	
	private ClienteRepository clienteRepository;

	@Transactional // anotação que desfaz toda transação no momento de persistencia caso dê algo
					// errado;
	public Cliente salvar(Cliente cliente) {
		boolean emailEmUso = clienteRepository.findByEmail(cliente.getEmail()).stream()
				.anyMatch(clienteExistente -> !clienteExistente.equals(cliente));
		if (emailEmUso) {

			throw new NegocioException("Já existe cliente cadastrado com este email.");
		}

		return clienteRepository.save(cliente);
	}

	@Transactional
	public void deletar(Long id) {
		clienteRepository.deleteById(id);
	}

	public Cliente buscar(Long id) {
		Cliente cliente = clienteRepository.findById(id)
				.orElseThrow(() -> new NegocioException("Cliente não encontrado."));
		return cliente;
	}
}
