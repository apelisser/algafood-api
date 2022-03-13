package com.algafood.api.openapi.controller;

import java.util.List;

import org.springframework.http.MediaType;

import com.algafood.api.exceptionhandler.Problem;
import com.algafood.api.model.PermissaoModel;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Api(tags = "Grupos")
public interface GrupoPermissaoControllerOpenApi {

	@Operation(summary = "Lista as permissões associadas ao grupo")
	@ApiResponses({
		@ApiResponse(responseCode = "400", description = "ID do grupo, inválido", 
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "404", description = "Grupo não encontrado",
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Problem.class)))
	})
	public List<PermissaoModel> listar(
			@Parameter(description = "ID do grupo", example = "1")
			Long grupoId);
	
	@Operation(summary = "Associação de permissão com grupo")
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Associação realizada com sucesso"),
		@ApiResponse(responseCode = "404", description = "Grupo ou permissão não encontrada",
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Problem.class)))
	})
	public void associar(
			@Parameter(description = "ID do grupo", example = "1")
			Long grupoId, 
			
			@Parameter(description = "ID da permissão", example = "1")
			Long permissaoId);
	
	@Operation(summary = "Associação de permissão com grupo")
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "desassociação realizada com sucesso"),
		@ApiResponse(responseCode = "404", description = "Grupo ou permissão não encontrada",
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Problem.class)))
	})
	public void desassociar(
			@Parameter(description = "ID do grupo", example = "1")
			Long grupoId, 
			
			@Parameter(description = "ID da permissão", example = "1")
			Long permissaoId);
}
