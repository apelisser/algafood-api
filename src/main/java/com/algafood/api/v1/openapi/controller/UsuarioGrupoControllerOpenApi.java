package com.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.algafood.api.exceptionhandler.Problem;
import com.algafood.api.v1.model.GrupoModel;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


@Api(tags = "Usuários")
public interface UsuarioGrupoControllerOpenApi {

	@Operation(summary = "Lista as permissões associadas ao usuário")
	@ApiResponses({
		@ApiResponse(responseCode = "400", description = "ID do usuário, inválido", 
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "404", description = "Usuário não encontrado",
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Problem.class)))
	})
	public CollectionModel<GrupoModel> listar(
			@Parameter(description = "ID do usuário", example = "1", required = true)
			Long usuarioId);
	
	@Operation(summary = "Desassociação de usuário com grupo")
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "desassociação realizada com sucesso"),
		@ApiResponse(responseCode = "404", description = "Usuário ou grupo não encontrado",
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Problem.class)))
	})
	public ResponseEntity<Void> desassociar(
			@Parameter(description = "ID do usuário", example = "1")
			Long usuarioId, 
			
			@Parameter(description = "ID do grupo", example = "1")
			Long grupoId);
	
	@Operation(summary = "Associação de usuário com grupo")
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Associação realizada com sucesso"),
		@ApiResponse(responseCode = "404", description = "Usuário ou grupo não encontrado",
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Problem.class)))
	})
	public ResponseEntity<Void> associar(
			@Parameter(description = "ID do usuário", example = "1")
			Long usuarioId, 
			
			@Parameter(description = "ID do grupo", example = "1")
			Long grupoId);
	
}
