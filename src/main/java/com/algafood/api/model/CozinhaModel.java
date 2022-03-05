package com.algafood.api.model;

import com.algafood.api.model.view.RestauranteView;
import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
public class CozinhaModel {

	@Schema(example = "1")
	@JsonView(RestauranteView.Resumo.class)
	private Long id;
	
	@Schema(example = "Brasileira")
	@JsonView(RestauranteView.Resumo.class)
	private String nome;
	
}
