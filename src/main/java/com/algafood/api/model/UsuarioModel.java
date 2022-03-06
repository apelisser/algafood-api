package com.algafood.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioModel {

	@ApiModelProperty(example = "1")
	private Long id;
	
	@ApiModelProperty(example = "João de Barro")
	private String nome;
	
	@ApiModelProperty(example = "joao.barro@email.com")
	private String email;
	
}
