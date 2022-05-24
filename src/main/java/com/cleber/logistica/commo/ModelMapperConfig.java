package com.cleber.logistica.commo;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** esta configuração indica que este metodo será geranciado pelo spring por ser uma biblioteca de terceiro */
@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
