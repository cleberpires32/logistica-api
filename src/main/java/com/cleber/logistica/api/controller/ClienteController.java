package com.cleber.logistica.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cleber.logistica.domain.model.Cliente;
import com.cleber.logistica.domain.repository.ClienteRepository;
import com.cleber.logistica.domain.service.ClienteService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClienteController {

	private ClienteRepository clienteRepository;
	private ClienteService clienteService;

	@GetMapping
	public List<Cliente> listarClientes() {

		return clienteRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Cliente> buscarPorId(@PathVariable Long id) {

		return clienteRepository.findById(id).map(cliente -> ResponseEntity.ok(cliente))
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente adicionar(@Valid @RequestBody Cliente cliente) {
		return clienteService.salvar(cliente);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Cliente> atualizar(@PathVariable Long id, @Valid @RequestBody Cliente cliente) {

		if (!clienteRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		cliente.setId(id);
		clienteService.salvar(cliente);
		return ResponseEntity.ok(cliente);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {

		if (!clienteRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		clienteService.deletar(id);
		return ResponseEntity.noContent().build();
	}
}
