package com.algafood.api.v2.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@ApiModel("CozinhaModel")
@Relation(collectionRelation = "cozinhas")
@Getter 
@Setter
public class CozinhaModelV2 extends RepresentationModel<CozinhaModelV2> {

	@Schema(example = "1")
//	@JsonView(RestauranteView.Resumo.class)
	private Long idCozinha;
	
	@Schema(example = "Brasileira")
//	@JsonView(RestauranteView.Resumo.class)
	private String nomeCozinha;
	
}
