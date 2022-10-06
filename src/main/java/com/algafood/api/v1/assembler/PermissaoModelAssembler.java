package com.algafood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.algafood.api.v1.AlgaLinks;
import com.algafood.api.v1.model.PermissaoModel;
import com.algafood.core.security.AlgaSecurity;
import com.algafood.domain.model.Permissao;

@Component
public class PermissaoModelAssembler implements RepresentationModelAssembler<Permissao, PermissaoModel> {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AlgaLinks algaLinks;
	
	@Autowired
	private AlgaSecurity algaSecurity;
	
	@Override
	public CollectionModel<PermissaoModel> toCollectionModel(Iterable<? extends Permissao> entities) {
		CollectionModel<PermissaoModel> collectionModel = RepresentationModelAssembler.super.toCollectionModel(entities);
		
		if (algaSecurity.podeConsultarUsuarioGruposPermissoes()) {
			collectionModel.add(algaLinks.linkToPermissoes());
		}
		
		return collectionModel;
	}
	
	@Override
	public  PermissaoModel toModel(Permissao permissao) {
		PermissaoModel permissaoModel = modelMapper.map(permissao, PermissaoModel.class);
		return permissaoModel;
	}
	
}
