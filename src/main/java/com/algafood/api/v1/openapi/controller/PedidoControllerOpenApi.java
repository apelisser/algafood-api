package com.algafood.api.v1.openapi.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

import com.algafood.api.v1.model.PedidoModel;
import com.algafood.api.v1.model.PedidoResumoModel;
import com.algafood.api.v1.model.input.PedidoInput;
import com.algafood.domain.filter.PedidoFilter;

public interface PedidoControllerOpenApi {

	public PagedModel<PedidoResumoModel> pesquisar(PedidoFilter filtro, Pageable pageable);

	public PedidoModel buscar(String codigoPedido);

	public PedidoModel adicionar(PedidoInput pedidoInput);

}
