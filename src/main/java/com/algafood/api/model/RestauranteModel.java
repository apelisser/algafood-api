package com.algafood.api.model;

import java.math.BigDecimal;

import com.algafood.api.model.view.RestauranteView;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RestauranteModel {

	@JsonView({
		RestauranteView.Resumo.class, 
		RestauranteView.ApenasNome.class})
	private Long id;
	
	@JsonView({
		RestauranteView.Resumo.class,
		RestauranteView.ApenasNome.class})
	private String nome;
	
	@JsonView(RestauranteView.Resumo.class)
	private BigDecimal taxaFrete;
	
	@JsonView(RestauranteView.Resumo.class)
	private CozinhaModel cozinha;
	private Boolean aberto;
	private Boolean ativo;
	private EnderecoModel endereco;

}
