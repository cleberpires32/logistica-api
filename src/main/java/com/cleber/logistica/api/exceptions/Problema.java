package com.cleber.logistica.api.exceptions;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)//anotação que retira da throw mensagem campos que não tem informação.
public class Problema {

	private Integer status;
	private LocalDateTime localDateTime;
	private String titulo;

	private List<Campo> campo = new ArrayList<>();
	

	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}


	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}


	public void setLocalDateTime(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}


	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public List<Campo> getCampo() {
		return campo;
	}


	public void setCampo(List<Campo> campo) {
		this.campo = campo;
	}
}
