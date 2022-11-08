package com.algafood.api.v1.openapi.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

import com.algafood.api.v1.model.PedidoModel;
import com.algafood.api.v1.model.PedidoResumoModel;
import com.algafood.api.v1.model.input.PedidoInput;
import com.algafood.core.springdoc.PageableParameter;
import com.algafood.domain.filter.PedidoFilter;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "security_auth")
public interface PedidoControllerOpenApi {

	@PageableParameter
	public PagedModel<PedidoResumoModel> pesquisar(PedidoFilter filtro, 
			@Parameter(hidden = true)
			Pageable pageable);

	public PedidoModel buscar(String codigoPedido);

	public PedidoModel adicionar(PedidoInput pedidoInput);

}
