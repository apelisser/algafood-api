package com.algafood.api.v1.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "pedidos")
@Setter
@Getter
public class PedidoModel extends RepresentationModel<PedidoModel> {

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
	
	@ApiModelProperty(example = "2022-03-06T17:17:03Z")
	private OffsetDateTime dataConfirmacao;
	
	@ApiModelProperty(example = "2022-03-06T17:40:30Z")
	private OffsetDateTime dataEntrega;
	
	@ApiModelProperty(example = "2022-03-06T17:15:30Z")
	private OffsetDateTime dataCancelamento;
	
	private RestauranteApenasNomeModel restaurante;
	
	private UsuarioModel cliente;
	
	private FormaPagamentoModel formaPagamento;
	
	private EnderecoModel enderecoEntrega;
	
	private List<ItemPedidoModel> itens;
	
}
