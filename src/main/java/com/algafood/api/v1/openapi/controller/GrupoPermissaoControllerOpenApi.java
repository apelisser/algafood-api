package com.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.algafood.api.v1.model.PermissaoModel;

public interface GrupoPermissaoControllerOpenApi {

	public CollectionModel<PermissaoModel> listar(Long grupoId);

	public ResponseEntity<Void> associar(Long grupoId, Long permissaoId);

	public ResponseEntity<Void> desassociar(Long grupoId, Long permissaoId);
	
}
