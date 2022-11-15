package com.algafood.api.v1.openapi.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;

import com.algafood.api.v1.model.FotoProdutoModel;
import com.algafood.api.v1.model.input.FotoProdutoInput;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "security_auth")
public interface RestauranteProdutoFotoControllerOpenApi {

	
	@Operation(summary = "Atualiza a doto de um produto de um restaurante")
	public FotoProdutoModel atualizarFoto(
			@Parameter(description = "Id do restaurante", example = "1", required = true)
			Long restauranteId, 
			@Parameter(description = "Id do produto", example = "2", required = true)
			Long produtoId, 
			@RequestBody(required = true)
			FotoProdutoInput fotoProdutoInput) throws IOException;

	@Operation(summary = "Buscar a foto do produto de um restaurante", responses = {
			@ApiResponse(responseCode = "200", content = {
					@Content(mediaType = "aplication/json", schema = @Schema(implementation = FotoProdutoModel.class)),
					@Content(mediaType = "image/jpeg", schema = @Schema(type = "string", format = "binary")),
					@Content(mediaType = "image/png", schema = @Schema(type = "string", format = "binary"))
			})
	})
	public FotoProdutoModel buscar(Long restauranteId, Long produtoId);

	@Operation(hidden = true)
	public ResponseEntity<?> servir(Long restauranteId, Long produtoId, String acceptHeader)
			throws HttpMediaTypeNotAcceptableException;

	public void excluir(Long restauranteId, Long produtoId);

}
