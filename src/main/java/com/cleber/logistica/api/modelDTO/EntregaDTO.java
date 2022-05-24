package com.cleber.logistica.api.modelDTO;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.cleber.logistica.api.imputDTO.ClienteIdInput;
import com.cleber.logistica.domain.model.Destinatario;
import com.cleber.logistica.domain.model.StatusEntrega;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntregaDTO {

	private Long id;
	private ClienteIdInput cliente;
	private Destinatario destinatario;
	private BigDecimal taxa;
	private StatusEntrega statusEntrega;
	private OffsetDateTime dataPedido;

}
