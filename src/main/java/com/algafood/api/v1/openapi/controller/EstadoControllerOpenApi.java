package com.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.algafood.api.v1.model.EstadoModel;
import com.algafood.api.v1.model.input.EstadoInput;

public interface EstadoControllerOpenApi {

	public CollectionModel<EstadoModel> listar();

	public EstadoModel buscar(Long estadoId);

	public EstadoModel adicionar(EstadoInput estadoInput);

	public EstadoModel atualizar(Long estadoId, EstadoInput estadoInput);

	public void remover(Long estadoId);

}
