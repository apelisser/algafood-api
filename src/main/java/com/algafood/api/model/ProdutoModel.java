package com.algafood.api.model;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoModel {

	@Schema(example = "1")
	private Long id;

	@Schema(example = "Bolo de fubá")
	private String nome;

	@Schema(example = "Assado no dia")
	private String descricao;

	@Schema(example = "23.9")
	private BigDecimal preco;

	@Schema(example = "true")
	private Boolean ativo;
	
}
