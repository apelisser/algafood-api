package com.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;

import com.algafood.api.exceptionhandler.Problem;
import com.algafood.api.v1.model.ProdutoModel;
import com.algafood.api.v1.model.input.ProdutoInput;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Api(tags = "Produtos")
public interface RestauranteProdutoControllerOpenApi {

	@Operation(summary = "Lista os produtos de um restaurante")
	@ApiResponses({
		@ApiResponse(responseCode = "404", description = "Restaurante não encontrado",
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Problem.class)))
	})
	public CollectionModel<ProdutoModel> listar(
			@Parameter(description = "ID de um restaurante", example = "1", required = true)
			Long restauranteId,

			@Parameter(description = "Indica se deve ou não incluir produtos inativos no resultado da listagem",
				example = "false", content = @Content(schema = @Schema(defaultValue = "false")))
			Boolean incluirInativos);
	
	@Operation(summary = "Busca um produto de um restaurante")
	@ApiResponses({
		@ApiResponse(responseCode = "400", description = "ID do restaurante, inválido",
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "404", description = "Restaurante ou produto não encontrado",
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Problem.class)))
	})
	public ProdutoModel buscar(
			@Parameter(description = "ID de um restaurante", example = "1", required = true)
			Long restauranteId,
			
			@Parameter(description = "ID de um produto", example = "1", required = true)
			Long produtoId);
	
	@Operation(summary = "Cadastra um produto de um restaurante")
	@ApiResponses({
		@ApiResponse(responseCode = "201", description = "Produto cadastrado"),
		@ApiResponse(responseCode = "404", description = "Restaurante não encontrado",
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Problem.class)))
	})
	public ProdutoModel adicionar(
			@Parameter(description = "ID de um restaurante", example = "1", required = true)
			Long restauranteId, 
			
			@Parameter(name = "corpo", description = "Representação de um novo produto", required = true)
			ProdutoInput protudoInput);
	

	@Operation(summary = "Atualiza um produto de um restaurante")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Produto cadastrado"),
		@ApiResponse(responseCode = "404", description = "Restaurante ou produto não encontrado",
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Problem.class)))
	})
	public ProdutoModel atualizar(
			@Parameter(description = "ID de um restaurante", example = "1", required = true)
			Long restauranteId,
			
			@Parameter(description = "ID de um produto", example = "1", required = true)
			Long produtoId, 

			@Parameter(name = "corpo", description = "Representação de um novo produto", required = true)
			ProdutoInput protudoInput);
	
}
