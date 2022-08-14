package com.algafood.api.v1.model.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoInput {
	
	@ApiModelProperty(example = "38400-000", required = true)
	@NotBlank
	private String cep;
	
	@ApiModelProperty(example = "Rua Submarino Afundado", required = true)
	@NotBlank
	private String logradouro;
	
	@ApiModelProperty(example = "123", required = true)
	@NotBlank
	private String numero;
	
	@ApiModelProperty(example = "Centro")
	private String complemento;
	
	@ApiModelProperty(example = "Bairro Batalha Naval", required = true)
	@NotBlank
	private String bairro;
	
	@Valid
	@NotNull
	private CidadeIdInput cidade;
	
}
