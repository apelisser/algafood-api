package com.algafood.api.model.input;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.algafood.core.validation.TaxaFrete;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestauranteInput {
	
	@ApiModelProperty(example = "Restaurante Forno a Lenha")
	@NotBlank
	private String nome;
	
	@ApiModelProperty(example = "7.50")
	@NotNull
	@TaxaFrete
	private BigDecimal taxaFrete;
	
	@Valid
	@NotNull
	private CozinhaIdInput cozinha;
	
	@Valid
	@NotNull
	private EnderecoInput endereco;
	
}
