package com.algafood.api.v1.model.input;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioComSennhaInput extends UsuarioInput {

	@NotBlank
	private String senha;
	
}
