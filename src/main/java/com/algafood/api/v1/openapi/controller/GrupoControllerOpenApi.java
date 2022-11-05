package com.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.algafood.api.v1.model.GrupoModel;
import com.algafood.api.v1.model.input.GrupoInput;

public interface GrupoControllerOpenApi {

	public CollectionModel<GrupoModel> listar();

	public GrupoModel buscar(Long grupoId);

	public GrupoModel adicionar(GrupoInput grupoInput);

	public GrupoModel atualizar(Long grupoId, GrupoInput grupoInput);

	public void remover(Long grupoId);
	
}
