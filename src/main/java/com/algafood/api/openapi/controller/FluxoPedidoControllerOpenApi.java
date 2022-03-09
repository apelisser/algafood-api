package com.algafood.api.openapi.controller;

import org.springframework.http.MediaType;

import com.algafood.api.exceptionhandler.Problem;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Api(tags = "Pedidos")
public interface FluxoPedidoControllerOpenApi {
	
	@Operation(summary = "Confirmação de pedidos")
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Pedido confirmado com sucesso"),
		@ApiResponse(responseCode = "404", description = "Pedido não encotrado", 
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Problem.class)))
	})
	public void confirmar(
			@Parameter(description = "Código de um pedido", example = "609abd53-913c-48a3-bd0a-ae95317facba", required = true)
			String codigoPedido);

	@Operation(summary = "Cancelamento de pedidos")
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Pedido cancelado com sucesso"),
		@ApiResponse(responseCode = "404", description = "Pedido não encotrado", 
		content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Problem.class)))
	})
	public void cancelar(
			@Parameter(description = "Código de um pedido", example = "609abd53-913c-48a3-bd0a-ae95317facba", required = true)
			String codigoPedido);
	
	@Operation(summary = "Registra entrega de pedidos")
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Entrega do pedido registrada com sucesso"),
		@ApiResponse(responseCode = "404", description = "Pedido não encotrado", 
		content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Problem.class)))
	})
	public void entregar(
			@Parameter(description = "Código de um pedido", example = "609abd53-913c-48a3-bd0a-ae95317facba", required = true)
			String codigoPedido);
	
}
