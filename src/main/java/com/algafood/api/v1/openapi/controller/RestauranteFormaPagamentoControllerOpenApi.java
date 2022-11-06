package com.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.algafood.api.v1.model.FormaPagamentoModel;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "security_auth")
public interface RestauranteFormaPagamentoControllerOpenApi {

	public CollectionModel<FormaPagamentoModel> listar(Long restauranteId);

	public ResponseEntity<Void> desassociar(Long restauranteId, Long formaPagamentoId);

	public ResponseEntity<Void> associar(Long restauranteId, Long formaPagamentoId);

}
