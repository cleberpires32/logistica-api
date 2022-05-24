package com.cleber.logistica.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cleber.logistica.domain.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	List<Cliente> findByNome(String nome);
	
	Optional<Cliente> findByEmail(String email);
	
	
}
