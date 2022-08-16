package com.algafood.api.v2.model.input;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@ApiModel("CozinhaInput")
@Getter
@Setter
public class CozinhaInputV2 {

	@Schema(example = "Japonesa", required = true)
	@NotBlank
	private String nomeCozinha;
	
}
