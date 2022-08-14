package com.algafood.api.v1.model.input;

import javax.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioComSennhaInput extends UsuarioInput {

	@Schema(example = "123", required = true)
	@NotBlank
	private String senha;
	
}
