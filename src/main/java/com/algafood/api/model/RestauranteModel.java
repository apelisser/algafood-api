package com.algafood.api.model;

import java.math.BigDecimal;

import com.algafood.api.model.view.RestauranteView;
import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RestauranteModel {

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
	private Boolean aberto;
	private Boolean ativo;
	private EnderecoModel endereco;

}
