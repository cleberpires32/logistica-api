package com.cleber.logistica.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import com.cleber.logistica.domain.exception.NegocioException;
import com.cleber.logistica.domain.groups.ValidationGroups;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Entrega {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Valid
	@NotNull
	@ManyToOne
	@ConvertGroup(from = Default.class, to = ValidationGroups.ClientId.class)
	private Cliente cliente;

	@Embedded
	@NotNull
	@Valid
	private Destinatario destinatario;

	@OneToMany(mappedBy = "entrega", cascade = CascadeType.ALL)
	private List<Ocorrencia> ocorrencia = new ArrayList<>();

	private BigDecimal taxa;

	@JsonProperty(access = Access.READ_ONLY)
	@Enumerated(EnumType.STRING)
	@Column(name = "status_entrega")
	private StatusEntrega statusEntrega;

	@JsonProperty(access = Access.READ_ONLY)
	private OffsetDateTime dataPedido;

	@JsonProperty(access = Access.READ_ONLY)
	private OffsetDateTime dataFinalizacao;

	public Ocorrencia adicionarOcorrencia(String descricao) {

		Ocorrencia ocorrencia = new Ocorrencia();
		ocorrencia.setDataRegistro(OffsetDateTime.now());
		ocorrencia.setDescricao(descricao);
		ocorrencia.setEntrega(this);
		this.ocorrencia.add(ocorrencia);
		return ocorrencia;

	}

	@SuppressWarnings("static-access")
	public void finalizar() {
		if(naoPodeSerFinalizada()) {
			throw new NegocioException("A entrega não pode ser finalizada.");
		}
		
		setStatusEntrega(statusEntrega.FINALIZADA);
		setDataFinalizacao(OffsetDateTime.now());
	}

	public boolean naoPodeSerFinalizada() {
		return !StatusEntrega.PENDENTE.equals(getStatusEntrega());
	}
	
	public boolean podeSerFinalizada() {
		return StatusEntrega.PENDENTE.equals(getStatusEntrega());
	} 
}
