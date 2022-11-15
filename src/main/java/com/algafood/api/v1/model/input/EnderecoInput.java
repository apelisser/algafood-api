package com.algafood.api.v1.model.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoInput {
	
	@Schema(example = "19000-123")
	@NotBlank
	private String cep;
	
	@Schema(example = "Rua Quatorze de Dezembro")
	@NotBlank
	private String logradouro;
	
	@Schema(example = "999")
	@NotBlank
	private String numero;
	
	@Schema(example = "Apto 07")
	private String complemento;
	
	@Schema(example = "Centro")
	@NotBlank
	private String bairro;
	
	@Valid
	@NotNull
	private CidadeIdInput cidade;
	
}
