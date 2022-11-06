package com.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.algafood.api.v1.model.GrupoModel;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "security_auth")
public interface UsuarioGrupoControllerOpenApi {

	public CollectionModel<GrupoModel> listar(Long usuarioId);

	public ResponseEntity<Void> desassociar(Long usuarioId, Long grupoId);

	public ResponseEntity<Void> associar(Long usuarioId, Long grupoId);

}
