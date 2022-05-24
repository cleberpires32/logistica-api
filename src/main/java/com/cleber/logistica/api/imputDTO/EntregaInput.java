package com.cleber.logistica.api.imputDTO;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntregaInput {

	@NotNull
	@Valid
	private ClienteIdInput clienteId;
	@NotNull
	@Valid
	private DestinatarioInput destinatario;
}
