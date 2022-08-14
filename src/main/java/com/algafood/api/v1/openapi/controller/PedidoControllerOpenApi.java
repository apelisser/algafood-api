package com.algafood.api.v1.openapi.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

import com.algafood.api.v1.model.PedidoModel;
import com.algafood.api.v1.model.PedidoResumoModel;
import com.algafood.api.v1.model.input.PedidoInput;
import com.algafood.domain.filter.PedidoFilter;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@Api(tags = "Pedidos")
public interface PedidoControllerOpenApi {

	@Operation(summary = "Pesquisa os filtro")
	@ApiImplicitParams({
		@ApiImplicitParam(value = "Nomes das propriedades para filtrar, separados por vírgula",
				name = "campos", paramType = "query", dataTypeClass =  String.class)
	})
	public PagedModel<PedidoResumoModel> pesquisar(PedidoFilter filtro, Pageable pageable);
	
	@Operation(summary = "Busca um pedido por código")
	@ApiImplicitParams({
		@ApiImplicitParam(value = "Nomes das propriedades para filtrar, separados por vírgula",
				name = "campos", paramType = "query", dataTypeClass =  String.class)
	})
	public PedidoModel buscar(
			@Parameter(description = "Código de um pedido", example = "609abd53-913c-48a3-bd0a-ae95317facba")
			String codigoPedido);
	
	@Operation(summary = "Registra um pedido")
	public PedidoModel adicionar(
			@Parameter(name = "corpo", description = "Representação de um novo pedido")
			PedidoInput pedidoInput);

}
