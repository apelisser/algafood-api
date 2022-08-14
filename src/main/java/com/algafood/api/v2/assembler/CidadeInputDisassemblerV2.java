package com.algafood.api.v2.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algafood.api.v2.model.input.CidadeInputV2;
import com.algafood.domain.model.Cidade;
import com.algafood.domain.model.Estado;

@Component
public class CidadeInputDisassemblerV2 {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Cidade toDomainObject(CidadeInputV2 cidadeInput) {
		return modelMapper.map(cidadeInput, Cidade.class);
	}
	
	public void copyToDomainObject(CidadeInputV2 cidadeInput, Cidade cidade) {
		// Para evitar:
		//org.hibernate.HibernateException: identifier of an instance of com.algafood.domain.model.Estado was altered from 2 to 1
		cidade.setEstado(new Estado());
		
		modelMapper.map(cidadeInput, cidade);
	}

}
