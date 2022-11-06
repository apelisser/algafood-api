package com.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.algafood.api.v1.model.CidadeModel;
import com.algafood.api.v1.model.input.CidadeInput;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Cidades")
public interface CidadeControllerOpenApi {

	@Operation(summary = "Lista as cidades")
	public CollectionModel<CidadeModel> listar();
	
	@Operation(summary = "Busca uma cidade por ID")
	public CidadeModel buscar(Long cidadeId);
	
	@Operation(summary = "Cadastra uma cidade", 
			description = "Cadastro de uma cidade, necessita de um estado e um nome válido")
	public CidadeModel adicionar(CidadeInput cidadeInput);
	
	@Operation(summary = "Atualiza uma cidade por ID")
	public CidadeModel atualizar(Long cidadeId, CidadeInput cidadeInput);

	@Operation(summary = "Exclui uma cidade pelo ID")
	public void remover(Long cidadeId);
}
