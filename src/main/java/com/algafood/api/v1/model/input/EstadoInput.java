package com.algafood.api.v1.model.input;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstadoInput {

	@ApiModelProperty(example = "São Paulo")
	@NotBlank
	private String nome;
	
}
