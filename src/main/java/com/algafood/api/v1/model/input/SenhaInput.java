package com.algafood.api.v1.model.input;

import javax.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SenhaInput {
	
	@Schema(example = "123123", type = "string")
	@NotBlank
	private String senhaAtual;
	
	@Schema(example = "321321", type = "string")
	@NotBlank
	private String novaSenha;
}
