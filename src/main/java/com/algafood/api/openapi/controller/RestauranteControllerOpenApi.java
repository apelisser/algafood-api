package com.algafood.api.openapi.controller;

import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.algafood.api.exceptionhandler.Problem;
import com.algafood.api.model.RestauranteApenasNomeModel;
import com.algafood.api.model.RestauranteBasicoModel;
import com.algafood.api.model.RestauranteModel;
import com.algafood.api.model.input.RestauranteInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


@Api(tags = "Restaurantes")
public interface RestauranteControllerOpenApi {

	@Operation(summary = "Lista restaurantes")
	@ApiImplicitParams({
		@ApiImplicitParam(value = "Nome da projeção de pedidos", name = "projecao", 
				paramType = "query", dataTypeClass = String.class, allowableValues = "apenas-nome")
	})
	public CollectionModel<RestauranteBasicoModel> listar();
	
	@Operation(summary = "Lista restaurantes", hidden = true)
	public CollectionModel<RestauranteApenasNomeModel> listarApenasNomes();

	@Operation(summary = "Busca um restaurante por ID")
	@ApiResponses({
		@ApiResponse(responseCode = "400", description = "ID do restaurante, inválido",
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "404", description = "Restaurante não encontrado",
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Problem.class)))
	})
	public RestauranteModel buscar(
			@Parameter(description = "ID de um restaurante", example = "1", required = true)
			Long restauranteId);

	@Operation(summary = "Cadastra um restaurante")
	@ApiResponses({@ApiResponse(responseCode = "201", description = "Restaurante cadastrado")})
	public RestauranteModel adicionar(
			@Parameter(name = "corpo", description = "Representação de um novo restaurante", required = true)
			RestauranteInput restauranteInput);

	@Operation(summary = "Atualiza um restaurante por ID")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Restaurante atualizado"),
		@ApiResponse(responseCode = "404", description = "Restaurante não encontrado",
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Problem.class)))
	})
	public RestauranteModel atualizar(
			@Parameter(description = "ID de um restaurante", example = "1", required = true)
			Long restauranteId, 
			
			@Parameter(name = "corpo", description = "Representação de um restaurante com os novos dados", required = true)
			RestauranteInput restauranteInput);

	@Operation(summary = "Remove um restaurante por ID")
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Restaurante excluído"),
		@ApiResponse(responseCode = "404", description = "Restaurante não encontrado",
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Problem.class)))
	})
	public void remover(
			@Parameter(description = "ID de um restaurante", example = "1", required = true)
			Long restauranteId);

	@Operation(summary = "Ativa um restaurante por ID")
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Restaurante ativado com sucesso"),
		@ApiResponse(responseCode = "404", description = "Restaurante não encontrado",
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Problem.class)))
	})
	public ResponseEntity<Void> ativar(
			@Parameter(description = "ID de um restaurante", example = "1", required = true)
			Long restauranteId);
	
	@Operation(summary = "Desativa um restaurante por ID")
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Restaurante desativado com sucesso"),
		@ApiResponse(responseCode = "404", description = "Restaurante não encontrado",
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Problem.class)))
	})
	public ResponseEntity<Void> inativar(
			@Parameter(description = "ID de um restaurante", example = "1", required = true)
			Long restauranteId);
	
	@Operation(summary = "Abre um restaurante por ID")
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Restaurante aberto com sucesso"),
		@ApiResponse(responseCode = "404", description = "Restaurante não encontrado",
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Problem.class)))
	})
	public ResponseEntity<Void> abrir(
			@Parameter(description = "ID de um restaurante", example = "1", required = true)
			Long restauranteId);
	
	@Operation(summary = "Fecha um restaurante por ID")
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Restaurante fechado com sucesso"),
		@ApiResponse(responseCode = "404", description = "Restaurante não encontrado",
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Problem.class)))
	})
	public ResponseEntity<Void> fechar(
			@Parameter(description = "ID de um restaurante", example = "1", required = true)
			Long restauranteId);
	
	@Operation(summary = "Ativa vários restaurantes por ID")
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Restaurantes ativados com sucesso"),
	})
	public void ativarMultiplos(
			@Parameter(description = "IDs de restaurantes", required = true)
			List<Long> restauranteIds);
	
	@Operation(summary = "Desativa vários restaurantes por ID")
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Restaurantes desativados com sucesso"),
	})
	public void inativarMultiplos(
			@Parameter(description = "IDs de restaurantes", required = true)
			List<Long> restauranteIds);

}
