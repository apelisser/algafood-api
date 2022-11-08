package com.algafood.api.v1.openapi.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

import com.algafood.api.v1.model.CozinhaModel;
import com.algafood.api.v1.model.input.CozinhaInput;
import com.algafood.core.springdoc.PageableParameter;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "security_auth")
public interface CozinhaControllerOpenApi {

	@PageableParameter
	public PagedModel<CozinhaModel> listar(
			@Parameter(hidden = true)
			Pageable pageable);
	
	public CozinhaModel buscar(Long cozinhaId);

	public CozinhaModel adicionar(CozinhaInput cozinhaInput);

	public CozinhaModel atualizar(Long cozinhaId, CozinhaInput cozinhaInput);

	public void remover(Long cozinhaId);

}
