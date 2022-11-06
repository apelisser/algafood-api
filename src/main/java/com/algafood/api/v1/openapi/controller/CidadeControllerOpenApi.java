package com.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.algafood.api.v1.model.CidadeModel;
import com.algafood.api.v1.model.input.CidadeInput;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Cidades")
public interface CidadeControllerOpenApi {

	public CollectionModel<CidadeModel> listar();
	
	public CidadeModel buscar(Long cidadeId);
	
	public CidadeModel adicionar(CidadeInput cidadeInput);
	
	public CidadeModel atualizar(Long cidadeId, CidadeInput cidadeInput);

	public void remover(Long cidadeId);
}
