package com.algafood.api.v2.openapi.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.MediaType;

import com.algafood.api.exceptionhandler.Problem;
import com.algafood.api.v2.model.CozinhaModelV2;
import com.algafood.api.v2.model.input.CozinhaInputV2;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Api(tags = "Cozinhas")
public interface CozinhaControllerV2OpenApi {

	@Operation(summary = "Lista as cozinhas")
	public PagedModel<CozinhaModelV2> listar(Pageable pageable);
	
	@Operation(summary = "Busca uma cozinha por ID")
	@ApiResponses({
		@ApiResponse(responseCode = "400", description = "ID da cozinha, inválido",
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "404", description = "Cozinha não encontrada",
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Problem.class)))
	})
	public CozinhaModelV2 buscar(
			@Parameter(description = "ID de uma cozinha", example = "1")
			Long cozinhaId);

	@Operation(summary = "Cadastra uma cozinha")
	@ApiResponses({@ApiResponse(responseCode = "201", description = "Cozinha cadastrada")})
	public CozinhaModelV2 adicionar(
			@ApiParam(name = "corpo", value = "Representação de uma nova cozinha") 
			CozinhaInputV2 cozinhaInput);

	@Operation(summary = "Atualiza uma cozinha por ID")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Cozinha atualizada"),
		@ApiResponse(responseCode = "404", description = "Cozinha não encontrada",
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Problem.class)))
	})
	public CozinhaModelV2 atualizar(
			@ApiParam(value = "ID de uma cozinha", example = "1")
			Long cozinhaId, 
			
			@ApiParam(name = "corpo", value = "Representação de uma cozinha com os novos dados")
			CozinhaInputV2 cozinhaInput);

	@Operation(summary = "Exclui uma cozinha por ID")
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Cozinha excluída"),
		@ApiResponse(responseCode = "404", description = "Cozinha não encotrada", 
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Problem.class)))
	})
	public void remover(
			@ApiParam(value = "ID de uma cozinha", example = "1") 
			Long cozinhaId);

}
