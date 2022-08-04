package com.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algafood.api.AlgaLinks;
import com.algafood.api.model.FotoProdutoModel;
import com.algafood.domain.model.FotoProduto;
import com.algafood.api.controller.RestauranteProdutoFotoController;

@Component
public class FotoProdutoModelAssembler extends RepresentationModelAssemblerSupport<FotoProduto, FotoProdutoModel> {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AlgaLinks algaLinks;
	
	
	public FotoProdutoModelAssembler() {
		super(RestauranteProdutoFotoController.class, FotoProdutoModel.class);
	}
	
	public FotoProdutoModel toModel(FotoProduto foto) {
		FotoProdutoModel fotoProdutoModel = modelMapper.map(foto, FotoProdutoModel.class);
		
		fotoProdutoModel.add(algaLinks.linkToFotoProduto(
				foto.getRestauranteId(), foto.getProduto().getId()));
		
		fotoProdutoModel.add(algaLinks.linkToRestauranteProduto(
				foto.getRestauranteId(), foto.getProduto().getId(), "produto"));
		
		return fotoProdutoModel;
	}
	
}
