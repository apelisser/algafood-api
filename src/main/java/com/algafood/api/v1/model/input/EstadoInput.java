package com.algafood.api.v1.model.input;

import javax.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstadoInput {

	@Schema(example = "São Paulo")
	@NotBlank
	private String nome;
	
}
