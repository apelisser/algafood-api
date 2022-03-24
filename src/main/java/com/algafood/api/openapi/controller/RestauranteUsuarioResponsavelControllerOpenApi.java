package com.algafood.api.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;

import com.algafood.api.exceptionhandler.Problem;
import com.algafood.api.model.UsuarioModel;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Api(tags = "Restaurantes")
public interface RestauranteUsuarioResponsavelControllerOpenApi {

	@Operation(summary = "Lista os usuários responsáveis associados a restaurante")
	@ApiResponses({
		@ApiResponse(responseCode = "404", description = "Restaurante não encontrado",
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Problem.class)))
	})
	public CollectionModel<UsuarioModel> listar(
			@Parameter(description = "ID de um restaurante", example = "1", required = true)
			Long restauranteId);

	@Operation(summary = "Associação de restaurante com usuário responsável")
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Associação realizada com sucesso"),
		@ApiResponse(responseCode = "404", description = "Restaurante ou usuário responsável não encotrado", 
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Problem.class)))
	})
	public void associar(
			@Parameter(description = "ID de um restaurante", example = "1", required = true)
			Long restauranteId, 
			
			@Parameter(description = "ID do usuário", example = "1", required = true)
			Long usuarioId);
	
	@Operation(summary = "Desassociação de restaurante com usuário responsável")
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Desassociação realizada com sucesso"),
		@ApiResponse(responseCode = "404", description = "Restaurante ou usuário responsável não encotrado", 
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Problem.class)))
	})	
	public void desassociar(			
			@Parameter(description = "ID de um restaurante", example = "1", required = true)
			Long restauranteId, 
			
			@Parameter(description = "ID do usuário", example = "1", required = true)
			Long usuarioId);
	
}
