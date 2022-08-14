package com.algafood.core.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.algafood.api.v2.model.input.CidadeInputV2;
import com.algafood.domain.model.Cidade;

@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper modelMapper() {
		var modelMapper = new ModelMapper();
		
		modelMapper.createTypeMap(CidadeInputV2.class, Cidade.class)
			.addMappings(mapper -> mapper.skip(Cidade::setId));
		
		return modelMapper;
	}
	
}
