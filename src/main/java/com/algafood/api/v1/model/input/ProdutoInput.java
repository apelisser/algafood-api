package com.algafood.api.v1.model.input;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoInput {

	@Schema(example = "Arroz temperado")
	@NotBlank
	private String nome;
	
	@Schema(example = "Tempero caseiro")
	@NotBlank
	private String descricao;
	
	@Schema(example = "18.60")
	@PositiveOrZero
	private BigDecimal preco;
	
	@Schema(example = "true")
	@NotNull
	private Boolean ativo;
	
}
