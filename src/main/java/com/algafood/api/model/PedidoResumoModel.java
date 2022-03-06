package com.algafood.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PedidoResumoModel {

	@ApiModelProperty(example = "609abd53-913c-48a3-bd0a-ae95317facba")
	private String codigo;
	
	@ApiModelProperty(example = "298.90")
	private BigDecimal subtotal;
	
	@ApiModelProperty(example = "5.00")
	private BigDecimal taxaFrete;
	
	@ApiModelProperty(example = "303.90")
	private BigDecimal valorTotal;
	
	@ApiModelProperty(example = "CRIADO")
	private String status;
	
	@ApiModelProperty(example = "2022-03-06T17:12:33Z")
	private OffsetDateTime dataCriacao;
	
	private RestauranteResumoModel restaurante;
	
	private UsuarioModel cliente;

}
