package com.algafood.api.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.ServletWebRequest;

import com.algafood.api.exceptionhandler.Problem;
import com.algafood.api.model.FormaPagamentoModel;
import com.algafood.api.model.input.FormaPagamentoInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Api(tags = "Formas Pagamento")
public interface FormaPagamentoControllerOpenApi {
	
	@Operation(summary = "Lista as formas de pagamento")
	public ResponseEntity<CollectionModel<FormaPagamentoModel>> listar(ServletWebRequest request);
	
	@Operation(summary = "Busca uma forma de pagamento por ID")
	@ApiResponses({
		@ApiResponse(responseCode = "400", description = "ID da forma de pagamento, inválido",
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "404", description = "Forma de pagamento não encontrada",
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Problem.class)))
	})
	public ResponseEntity<FormaPagamentoModel> buscar(
			@Parameter(description = "ID de uma forma de pagamento", example = "1")
			Long formaPagamentoId, ServletWebRequest request);
	
	@Operation(summary = "Cadastra uma forma de pagamento")
	@ApiResponses({@ApiResponse(responseCode = "201", description = "Forma de pagamento cadastrada")})
	public FormaPagamentoModel adicionar(
			@ApiParam(name = "corpo", value = "Representação de uma nova forma de pagamento")
			FormaPagamentoInput formaPagamentoInput);

	@Operation(summary = "Atualiza uma forma de pagamento por ID")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Forma de pagamento atualizada"),
		@ApiResponse(responseCode = "404", description = "Forma de pagamento não encontrada",
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Problem.class)))
	})
	public FormaPagamentoModel atualizar(
			@ApiParam(value = "ID de uma forma de pagamento", example = "1")
			Long formaPagamentoId, 
			
			@ApiParam(name = "corpo", value = "Representação de uma forma de pagamento com os novos dados")
			FormaPagamentoInput formaPagamentoInput);
	
	@Operation(summary = "Exclui uma forma de pagamento por ID")
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Forma de pagamento excluída"),
		@ApiResponse(responseCode = "404", description = "Forma de pagamento não encotrada", 
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Problem.class)))
	})
	public void remover(
			@ApiParam(value = "ID de uma forma de pagamento", example = "1") 
			Long formaPagamentoId);
	
}
