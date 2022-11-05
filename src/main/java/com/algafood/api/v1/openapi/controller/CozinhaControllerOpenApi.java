package com.algafood.api.v1.openapi.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

import com.algafood.api.v1.model.CozinhaModel;
import com.algafood.api.v1.model.input.CozinhaInput;

public interface CozinhaControllerOpenApi {

	public PagedModel<CozinhaModel> listar(Pageable pageable);
	
	public CozinhaModel buscar(Long cozinhaId);

	public CozinhaModel adicionar(CozinhaInput cozinhaInput);

	public CozinhaModel atualizar(Long cozinhaId, CozinhaInput cozinhaInput);

	public void remover(Long cozinhaId);

}
