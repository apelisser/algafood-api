package com.algafood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algafood.api.v1.model.input.CidadeInput;
import com.algafood.domain.model.Cidade;
import com.algafood.domain.model.Estado;

@Component
public class CidadeInputDisassembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Cidade toDomainObject(CidadeInput cidadeInput) {
		return modelMapper.map(cidadeInput, Cidade.class);
	}
	
	public void copyToDomainObject(CidadeInput cidadeInput, Cidade cidade) {
		// Para evitar:
		//org.hibernate.HibernateException: identifier of an instance of com.algafood.domain.model.Estado was altered from 2 to 1
		cidade.setEstado(new Estado());
		
		modelMapper.map(cidadeInput, cidade);
	}

}
