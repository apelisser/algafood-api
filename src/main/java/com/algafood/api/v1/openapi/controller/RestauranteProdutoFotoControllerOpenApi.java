package com.algafood.api.v1.openapi.controller;

import java.io.IOException;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.multipart.MultipartFile;

import com.algafood.api.exceptionhandler.Problem;
import com.algafood.api.v1.model.FotoProdutoModel;
import com.algafood.api.v1.model.input.FotoProdutoInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Api(tags = "Produtos")
public interface RestauranteProdutoFotoControllerOpenApi {

	@Operation(summary = "Atualiza a foto do produto de um restaurante")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Foto do produto atualizada"),
		@ApiResponse(responseCode = "404", description = "Produto de restaurante não encontrado", 
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Problem.class)))
	})
	public FotoProdutoModel atualizarFoto(
			@Parameter(description = "ID de um restaurante", example = "1", required = true)
			Long restauranteId, 
			
			@Parameter(description = "ID de um produto", example = "1", required = true)
			Long produtoId, 
			
			FotoProdutoInput fotoProdutoInput,
			
			@Parameter(description = "Arquivo da foto do produto (máximo 500KB, apenas JPG e PNG)", required = true)
			MultipartFile arquivo
			) throws IOException;
	
	@Operation(summary = "Busca a foto do produto de um restaurante")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "OK", content = {
				@Content(mediaType = MediaType.IMAGE_PNG_VALUE, schema = @Schema(implementation = FotoProdutoModel.class)),
				@Content(mediaType = MediaType.IMAGE_JPEG_VALUE, schema = @Schema(implementation = FotoProdutoModel.class)),
				@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = FotoProdutoModel.class))
		}),
		@ApiResponse(responseCode = "400", description = "ID do restaurante ou produto inválido", 
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "404", description = "Foto de produto não encontrada", 
		content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Problem.class)))
	})
	public FotoProdutoModel buscar(
			@Parameter(description = "ID de um restaurante", example = "1", required = true)
			Long restauranteId, 
			
			@Parameter(description = "ID de um produto", example = "1", required = true)
			Long produtoId);
	
	
	@ApiOperation(value = "Busca a foto do produto de um restaurante", hidden = true)
	public ResponseEntity<?> servir(Long restauranteId, Long produtoId, String acceptHeader) throws HttpMediaTypeNotAcceptableException;
	
	@Operation(summary = "Exclui a foto do produto de um restaurante")
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Foto do produto excluída"),
		@ApiResponse(responseCode = "400", description = "ID do restaurante ou produto inválido", 
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "404", description = "Foto de produto não encontrada", 
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Problem.class)))
	})
	public void excluir(
			@Parameter(description = "ID de um restaurante", example = "1", required = true)
			Long restauranteId, 
			
			@Parameter(description = "ID de um produto", example = "1", required = true)
			Long produtoId);

}
