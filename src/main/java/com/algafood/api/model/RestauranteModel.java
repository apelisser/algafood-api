package com.algafood.api.model;

import java.math.BigDecimal;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.algafood.api.model.view.RestauranteView;
import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "restaurantes")
@Setter
@Getter
public class RestauranteModel extends RepresentationModel<RestauranteModel> {

	@ApiModelProperty(example = "1")
	@JsonView({
		RestauranteView.Resumo.class, 
		RestauranteView.ApenasNome.class})
	private Long id;
	
	@ApiModelProperty(example = "Restaurante Forno a Lenha")
	@JsonView({
		RestauranteView.Resumo.class,
		RestauranteView.ApenasNome.class})
	private String nome;
	
	@ApiModelProperty(example = "5.00")
	@JsonView(RestauranteView.Resumo.class)
	private BigDecimal taxaFrete;
	
	@JsonView(RestauranteView.Resumo.class)
	private CozinhaModel cozinha;
	
	@ApiModelProperty(example = "false")
	private Boolean aberto;

	@ApiModelProperty(example = "true")
	private Boolean ativo;
	
	private EnderecoModel endereco;

}
