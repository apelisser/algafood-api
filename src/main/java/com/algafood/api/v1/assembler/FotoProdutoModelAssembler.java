package com.algafood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algafood.api.v1.AlgaLinks;
import com.algafood.api.v1.controller.RestauranteProdutoFotoController;
import com.algafood.api.v1.model.FotoProdutoModel;
import com.algafood.core.security.AlgaSecurity;
import com.algafood.domain.model.FotoProduto;

@Component
public class FotoProdutoModelAssembler extends RepresentationModelAssemblerSupport<FotoProduto, FotoProdutoModel> {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AlgaLinks algaLinks;
	
	@Autowired
	private AlgaSecurity algaSecurity;
	
	
	public FotoProdutoModelAssembler() {
		super(RestauranteProdutoFotoController.class, FotoProdutoModel.class);
	}
	
	public FotoProdutoModel toModel(FotoProduto foto) {
		FotoProdutoModel fotoProdutoModel = modelMapper.map(foto, FotoProdutoModel.class);
		
		// Quem pode consultar restaurantes, também pode consultar os produtos e fotos
		if (algaSecurity.podeConsultarRestaurantes()) {
			fotoProdutoModel.add(algaLinks.linkToFotoProduto(
					foto.getRestauranteId(), foto.getProduto().getId()));
			
			fotoProdutoModel.add(algaLinks.linkToRestauranteProduto(
					foto.getRestauranteId(), foto.getProduto().getId(), "produto"));
		}
		
		return fotoProdutoModel;
	}
	
}
