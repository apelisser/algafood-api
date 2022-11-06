package com.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.algafood.api.v1.model.UsuarioModel;
import com.algafood.api.v1.model.input.SenhaInput;
import com.algafood.api.v1.model.input.UsuarioComSennhaInput;
import com.algafood.api.v1.model.input.UsuarioInput;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "security_auth")
public interface UsuarioControllerOpenApi {

	public CollectionModel<UsuarioModel> listar();

	public UsuarioModel buscar(Long usuarioId);

	public UsuarioModel adicionar(UsuarioComSennhaInput usuarioInputComSenha);

	public UsuarioModel atualizar(Long usuarioId, UsuarioInput usuarioInput);

	public void remover(Long usuarioId);

	public void alterarSenha(Long usuarioId, SenhaInput senhaInput);

}
