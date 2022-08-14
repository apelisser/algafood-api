package com.algafood.api.v1.model.input;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioInput {

	@Schema(example = "Fulano Beltrano", required = true)
	@NotBlank
	private String nome;
	
	@Schema(example = "fulano.beltrano@email.com", required = true)
	@NotBlank
	@Email
	private String email;
	
}
