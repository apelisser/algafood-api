package com.algafood.api.model;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ItemPedidoModel {
	
	private Long produtoId;
	
	@ApiModelProperty(example = "Porção de batata frita")
	private String produtoNome;
	
	@ApiModelProperty(example = "3")
	private Integer quantidade;
	
	@ApiModelProperty(example = "15.70")
	private BigDecimal precoUnitario;
	
	@ApiModelProperty(example = "47.10")
	private BigDecimal precoTotal;
	
	@ApiModelProperty(example = "Enviar molho")
	private String observacao;

}
