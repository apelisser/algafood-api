package com.algafood.api.openapi.controller;

import java.util.List;

import org.springframework.http.MediaType;

import com.algafood.api.exceptionhandler.Problem;
import com.algafood.api.model.FormaPagamentoModel;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Api(tags = "Restaurantes")
public interface  RestauranteFormaPagamentoControllerOpenApi{

	@Operation(summary = "Lista as formas de pagamento associadas a restaurante")
	@ApiResponses({
		@ApiResponse(responseCode = "404", description = "Restaurante não encontrado",
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Problem.class)))
	})
	public List<FormaPagamentoModel> listar(
			@Parameter(description = "ID de um restaurante", example = "1", required = true)
			Long restauranteId);

	@Operation(summary = "Desassociação de restaurante com forma de pagamento")
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Desassociação realizada com sucesso"),
		@ApiResponse(responseCode = "404", description = "Restaurante ou forma de pagamento não encotrado", 
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Problem.class)))
	})
	public void desassociar(
			@Parameter(description = "ID de um restaurante", example = "1", required = true)
			Long restauranteId, 
			
			@Parameter(description = "ID da forma de pagamento", example = "1", required = true)
			Long formaPagamentoId);

	@Operation(summary = "Associação de restaurante com forma de pagamento")
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Desassociação realizada com sucesso"),
		@ApiResponse(responseCode = "404", description = "Restaurante ou forma de pagamento não encotrado", 
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Problem.class)))
	})
	public void associar(
			@Parameter(description = "ID de um restaurante", example = "1", required = true)
			Long restauranteId, 
			
			@Parameter(description = "ID da forma de pagamento", example = "1", required = true)
			Long formaPagamentoId);

}
