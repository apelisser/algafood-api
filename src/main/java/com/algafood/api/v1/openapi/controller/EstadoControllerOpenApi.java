package com.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;

import com.algafood.api.exceptionhandler.Problem;
import com.algafood.api.v1.model.EstadoModel;
import com.algafood.api.v1.model.input.EstadoInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Api(tags = "Estados")
public interface EstadoControllerOpenApi {

	@Operation(summary = "Lista os estados")
	public CollectionModel<EstadoModel> listar();

	@Operation(summary = "Busca um estado por ID")
	@ApiResponses({
		@ApiResponse(responseCode = "400", description = "ID do estado, inválido",
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "404", description = "Estado não encontrado",
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Problem.class)))
	})
	public EstadoModel buscar(
			@Parameter(description = "ID de um estado", example = "1", required = true)
			Long estadoId);

	@Operation(summary = "Cadastra um estado")
	@ApiResponses({@ApiResponse(responseCode = "201", description = "Estado cadastrado")})
	public EstadoModel adicionar(
			@ApiParam(name = "corpo", value = "Representação de um novo estado")
			EstadoInput estadoInput);

	@Operation(summary = "Atualiza um estado por ID")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Estado atualizado"),
		@ApiResponse(responseCode = "404", description = "Estado não encontrado",
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Problem.class)))
	})
	public EstadoModel atualizar(
			@Parameter(description = "ID de um estado", example = "1", required = true)
			Long estadoId, 
			
			@ApiParam(name = "corpo", value = "Representação de um estado com os novos dados")
			EstadoInput estadoInput);

	@Operation(summary = "Exclui um estado por ID")
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Estado excluído"),
		@ApiResponse(responseCode = "404", description = "Estado não encotrado", 
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Problem.class)))
	})
	public void remover(
			@Parameter(description = "ID de um estado", example = "1", required = true)
			Long estadoId);
	
}
