package com.cleber.logistica.api.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cleber.logistica.domain.exception.EntitdadeNaoEncontradaException;
import com.cleber.logistica.domain.exception.NegocioException;

@ControllerAdvice
public class ApiExceptionHandle extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		Problema ocorrencia = new Problema();
		ocorrencia.setLocalDateTime(LocalDateTime.now());
		ocorrencia.setStatus(status.value());
		ocorrencia.setTitulo("Verifique o campo quanto a obrigatoriedade.");

		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			String nome = ((FieldError) error).getField();
			String message = error.getDefaultMessage();

			ocorrencia.getCampo().add(new Campo(nome, message));

		}

		return handleExceptionInternal(ex, ocorrencia, headers, status, request);
	}
	
	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<Object> handleNegocioException(NegocioException ex, WebRequest web){
		HttpStatus status= HttpStatus.BAD_REQUEST;
		Problema ocorrencia = new Problema();
		ocorrencia.setLocalDateTime(LocalDateTime.now());
		ocorrencia.setStatus(status.value());
		ocorrencia.setTitulo(ex.getMessage());
		
		return handleExceptionInternal(ex, ocorrencia, new HttpHeaders(), status, web);
		
	}
	
	@ExceptionHandler(EntitdadeNaoEncontradaException.class)
	public ResponseEntity<Object> handleEntidadeNaoEncontraException(NegocioException ex, WebRequest web){
		HttpStatus status= HttpStatus.NOT_FOUND;
		Problema ocorrencia = new Problema();
		ocorrencia.setLocalDateTime(LocalDateTime.now());
		ocorrencia.setStatus(status.value());
		ocorrencia.setTitulo(ex.getMessage());
		
		return handleExceptionInternal(ex, ocorrencia, new HttpHeaders(), status, web);
		
	}
}
