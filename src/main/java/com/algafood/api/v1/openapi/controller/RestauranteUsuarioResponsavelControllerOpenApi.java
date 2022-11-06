package com.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.algafood.api.v1.model.UsuarioModel;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "security_auth")
public interface RestauranteUsuarioResponsavelControllerOpenApi {

	public CollectionModel<UsuarioModel> listar(Long restauranteId);

	public ResponseEntity<Void> associar(Long restauranteId, Long usuarioId);

	public ResponseEntity<Void> desassociar(Long restauranteId, Long usuarioId);

}
