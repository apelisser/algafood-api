package com.algafood.api.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;

import com.algafood.api.exceptionhandler.Problem;
import com.algafood.api.model.UsuarioModel;
import com.algafood.api.model.input.SenhaInput;
import com.algafood.api.model.input.UsuarioComSennhaInput;
import com.algafood.api.model.input.UsuarioInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Api(tags = "Usuários")
public interface UsuarioControllerOpenApi {

	@Operation(summary = "Lista os usuários")
	public CollectionModel<UsuarioModel> listar();

	@Operation(summary = "Busca um usuário por ID")
	@ApiResponses({
		@ApiResponse(responseCode = "400", description = "ID do usuário, inválido",
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "404", description = "Usuário não encontrado",
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Problem.class)))
	})
	public UsuarioModel buscar(
			@Parameter(description = "ID de um usuário", example = "1", required = true)
			Long usuarioId);

	@Operation(summary = "Cadastra um usuário")
	@ApiResponses({@ApiResponse(responseCode = "201", description = "Usuário cadastrado")})
	public UsuarioModel adicionar(
			@ApiParam(name = "corpo", value = "Representação de um novo usuário com senha")
			UsuarioComSennhaInput usuarioInputComSenha);

	@Operation(summary = "Atualiza um usuário por ID")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Usuário atualizado"),
		@ApiResponse(responseCode = "404", description = "Usuário não encontrado",
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Problem.class)))
	})
	public UsuarioModel atualizar(
			@Parameter(description = "ID de um usuário", example = "1", required = true)
			Long usuarioId, 
			
			@ApiParam(name = "corpo", value = "Representação de um usuário com novos dados")
			UsuarioInput usuarioInput);

	@Operation(summary = "Exclui um usuário por ID")
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Usuário excluído"),
		@ApiResponse(responseCode = "404", description = "Usuário não encotrado", 
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Problem.class)))
	})
	public void remover(
			@Parameter(description = "ID de um usuário", example = "1", required = true)
			Long usuarioId);

	@Operation(summary = "Atualiza a senha de um usuário")
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Senha alterada com sucesso"),
		@ApiResponse(responseCode = "404", description = "Usuário não encontrado",
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Problem.class)))
	})
	public void alterarSenha(
			@Parameter(description = "ID de um usuário", example = "1", required = true)
			Long usuarioId, 
			
			@ApiParam(name = "corpo", value = "Representação de uma nova senha", required = true)
			SenhaInput senhaInput);

}
