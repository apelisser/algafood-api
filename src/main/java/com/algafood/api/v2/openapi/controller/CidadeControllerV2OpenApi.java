package com.algafood.api.v2.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;

import com.algafood.api.exceptionhandler.Problem;
import com.algafood.api.v2.model.CidadeModelV2;
import com.algafood.api.v2.model.input.CidadeInputV2;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Api(tags = "Cidades")
public interface CidadeControllerV2OpenApi {

	@ApiOperation("Lista as cidades")
	public CollectionModel<CidadeModelV2> listar();
	
	@ApiOperation("Busca uma cidade por ID")
	@ApiResponses({
		@ApiResponse(responseCode = "400", description = "ID da cidade, inválido", 
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "404", description = "Cidade não encotrada", 
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Problem.class)))
	})
	public CidadeModelV2 buscar(
			@ApiParam(value = "ID de uma cidade", example = "1") 
			Long cidadeId);
	
	@ApiOperation("Cadastra uma cidade")
	@ApiResponses({
		@ApiResponse(responseCode = "201", description = "Cidade cadastrada", 
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
	})
	public CidadeModelV2 adicionar(
			@ApiParam(name = "corpo", value = "Representação de uma nova cidade") 
			CidadeInputV2 cidadeInput);
	
	@ApiOperation("Atualiza uma cidade por ID")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Cidade atualizada", 
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
		@ApiResponse(responseCode = "404", description = "Cidade não encotrada", 
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Problem.class)))
	})
	public CidadeModelV2 atualizar(
			@ApiParam(value = "ID de uma cidade", example = "1") 
			Long cidadeId, 
			
			@ApiParam(name = "corpo", value = "Representação de uma cidade com os novos dados") 
			CidadeInputV2 cidadeInput);

	@ApiOperation("Exclui uma cidade por ID")
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Cidade excluída"),
		@ApiResponse(responseCode = "404", description = "Cidade não encotrada", 
		content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Problem.class)))
	})
	public void remover(
			@ApiParam(value = "ID de uma cidade", example = "1")  
			Long cidadeId);
}
