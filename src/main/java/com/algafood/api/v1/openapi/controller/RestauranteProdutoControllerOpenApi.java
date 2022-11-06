package com.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.algafood.api.v1.model.ProdutoModel;
import com.algafood.api.v1.model.input.ProdutoInput;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "security_auth")
public interface RestauranteProdutoControllerOpenApi {

	public CollectionModel<ProdutoModel> listar(Long restauranteId, Boolean incluirInativos);

	public ProdutoModel buscar(Long restauranteId, Long produtoId);

	public ProdutoModel adicionar(Long restauranteId, ProdutoInput protudoInput);

	public ProdutoModel atualizar(Long restauranteId, Long produtoId, ProdutoInput protudoInput);

}
