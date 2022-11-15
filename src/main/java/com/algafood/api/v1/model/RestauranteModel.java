package com.algafood.api.v1.model;

import java.math.BigDecimal;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "restaurantes")
@Setter
@Getter
public class RestauranteModel extends RepresentationModel<RestauranteModel> {

	@Schema(example = "1")
	private Long id;
	
	@Schema(example = "Bom Brasil")
	private String nome;
	
	@Schema(example = "5.0")
	private BigDecimal taxaFrete;
	
	private CozinhaModel cozinha;
	
	@Schema(example = "true")
	private Boolean aberto;
	
	@Schema(example = "true")
	private Boolean ativo;
	
	private EnderecoModel endereco;

}
