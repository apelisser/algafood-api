package com.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.algafood.api.v1.model.UsuarioModel;
import com.algafood.api.v1.model.input.SenhaInput;
import com.algafood.api.v1.model.input.UsuarioComSennhaInput;
import com.algafood.api.v1.model.input.UsuarioInput;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Usuários")
public interface UsuarioControllerOpenApi {

	@Operation(summary = "Lista os usuários")
	public CollectionModel<UsuarioModel> listar();

	@Operation(summary = "Busca um usuário por ID", responses = {
			@ApiResponse(responseCode = "200"),
			@ApiResponse(responseCode = "400", description = "ID do usuário inválido", 
				content = @Content(schema = @Schema(ref = "Problema"))
			),
			@ApiResponse(responseCode = "404", description = "Usuário não encontrado", 
				content = @Content(schema = @Schema(ref = "Problema"))
			)
	})
	public UsuarioModel buscar(
			@Parameter(description = "ID do usuário", example = "1", required = true)
			Long usuarioId);

	@Operation(summary = "Cadastra um usuário", responses = {
			@ApiResponse(responseCode = "201", description = "Usuário cadastrado"),
	})
	public UsuarioModel adicionar(
			@RequestBody(description = "Representação de um novo usuário", required = true)
			UsuarioComSennhaInput usuarioInputComSenha);

	@Operation(summary = "Atualiza um usuário", responses = {
			@ApiResponse(responseCode = "200", description = "Usuário atualizado"),			
			@ApiResponse(responseCode = "404", description = "Usuário não encontrado", 
				content = @Content(schema = @Schema(ref = "Problema"))
			)
	})
	public UsuarioModel atualizar(
			@Parameter(description = "ID do usuário", example = "1", required = true)
			Long usuarioId, 
			@RequestBody(description = "Representação de um usuário com novos dados", required = true)
			UsuarioInput usuarioInput);

	@Operation(summary = "Remove um usuário", responses = {
			@ApiResponse(responseCode = "204", description = "Usuário removido com sucesso"),			
			@ApiResponse(responseCode = "404", description = "Usuário não encontrado", 
				content = @Content(schema = @Schema(ref = "Problema"))
			)
	})	
	public void remover(
			@Parameter(description = "ID do usuário", example = "1", required = true)
			Long usuarioId);

	@Operation(summary = "Atualiza senha de um usuário", responses = {
			@ApiResponse(responseCode = "204", description = "Senha alterada com sucesso"),			
			@ApiResponse(responseCode = "404", description = "Usuário não encontrado", 
				content = @Content(schema = @Schema(ref = "Problema"))
			)
	})
	public void alterarSenha(
			@Parameter(description = "ID do usuário", example = "1", required = true)
			Long usuarioId, 
			@RequestBody(description = "Representação de uma nova senha", required = true)
			SenhaInput senhaInput);

}
