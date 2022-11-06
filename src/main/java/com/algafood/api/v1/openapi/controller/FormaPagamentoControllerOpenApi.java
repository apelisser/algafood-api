package com.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.ServletWebRequest;

import com.algafood.api.v1.model.FormaPagamentoModel;
import com.algafood.api.v1.model.input.FormaPagamentoInput;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "security_auth")
public interface FormaPagamentoControllerOpenApi {

	public ResponseEntity<CollectionModel<FormaPagamentoModel>> listar(ServletWebRequest request);

	public ResponseEntity<FormaPagamentoModel> buscar(Long formaPagamentoId, ServletWebRequest request);

	public FormaPagamentoModel adicionar(FormaPagamentoInput formaPagamentoInput);

	public FormaPagamentoModel atualizar(Long formaPagamentoId, FormaPagamentoInput formaPagamentoInput);

}
