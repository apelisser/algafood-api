package com.algafood.api.openapi.controller;

import java.util.List;

import org.springframework.http.MediaType;

import com.algafood.api.exceptionhandler.Problem;
import com.algafood.api.model.GrupoModel;
import com.algafood.api.model.input.GrupoInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Api(tags = "Grupos")
public interface GrupoControllerOpenApi {

	@Operation(summary = "Lista os grupos")
	public List<GrupoModel> listar();
	
	@Operation(summary = "Busca um grupo por ID")
	@ApiResponses({
		@ApiResponse(responseCode = "400", description = "ID do grupo, inválido", 
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "404", description = "Grupo não encontrado",
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Problem.class)))
	})
	public GrupoModel buscar(
			@Parameter(description = "ID de um grupo", example = "1")
			Long grupoId);
	 
	@Operation(summary = "Cadastra um grupo")
	@ApiResponses({
		@ApiResponse(responseCode = "201", description = "Grupo cadastrado",
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = GrupoModel.class)))
	})
	public GrupoModel adicionar(
			@ApiParam(name = "corpo", value = "Representação de um novo grupo") 
			GrupoInput grupoInput);
	
	@Operation(summary = "Atualiza um grupo por ID")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Grupo atualizado", 
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
		@ApiResponse(responseCode = "404", description = "Grupo não encotrado", 
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Problem.class)))
	})
	public GrupoModel atualizar(
			@ApiParam(value = "ID de um grupo", example = "1") 
			Long grupoId,
			
			@ApiParam(name = "corpo", value = "Representação de um grupo com os novos dados") 
			GrupoInput grupoInput);
	
	@Operation(summary = "Exclui um grupo por ID")
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Grupo excluído"),
		@ApiResponse(responseCode = "404", description = "Grupo não encotrado", 
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Problem.class)))
	})
	public void remover(
			@ApiParam(value = "ID de um grupo", example = "1") 
			Long grupoId);
}
