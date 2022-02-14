package com.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algafood.api.assembler.CidadeInputDisassembler;
import com.algafood.api.assembler.CidadeModelAssembler;
import com.algafood.api.exceptionhandler.Problem;
import com.algafood.api.model.CidadeModel;
import com.algafood.api.model.input.CidadeInput;
import com.algafood.domain.exception.EstadoNaoEncontradoException;
import com.algafood.domain.exception.NegocioException;
import com.algafood.domain.model.Cidade;
import com.algafood.domain.repository.CidadeRepository;
import com.algafood.domain.service.CadastroCidadeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Api(tags = "Cidades")
@RestController
@RequestMapping(value = "/cidades")
public class CidadeController {

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private CadastroCidadeService cadastroCidade;
	
	@Autowired
	private CidadeInputDisassembler cidadeInputDisassembler;
	
	@Autowired
	private CidadeModelAssembler cidadeModelAssembler;

	@ApiOperation("Lista as cidades")
	@GetMapping
	public List<CidadeModel> listar() {
		return cidadeModelAssembler.toCollectionModel(cidadeRepository.findAll());
	}
	
	@ApiOperation("Busca uma cidade por ID")
	@ApiResponses({
		@ApiResponse(responseCode = "400", description = "ID da cidade inválido", 
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Problem.class))),
		@ApiResponse(responseCode = "404", description = "Cidade não encotrada", 
		content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Problem.class)))
	})
	@GetMapping("/{cidadeId}")
	public CidadeModel buscar(
		@ApiParam(value = "ID de uma cidade", example = "1") 
		@PathVariable Long cidadeId) {
		Cidade cidade = cadastroCidade.buscarOuFalhar(cidadeId);
		return cidadeModelAssembler.toModel(cidade);
	}
	
	@ApiOperation("Cadastra uma cidade")
	@ApiResponses({
		@ApiResponse(responseCode = "201", description = "Cidade cadastrada", 
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
	})
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CidadeModel adicionar(@ApiParam(name = "corpo", value = "Representação de uma nova cidade") 
		@RequestBody @Valid CidadeInput cidadeInput) {
		try {
			Cidade cidade = cidadeInputDisassembler.toDomainObject(cidadeInput);
			return cidadeModelAssembler.toModel(cadastroCidade.salvar(cidade));
		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@ApiOperation("Atualiza uma cidade por ID")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Cidade atualizada", 
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
		@ApiResponse(responseCode = "404", description = "Cidade não encotrada", 
		content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Problem.class)))
	})
	@PutMapping("/{cidadeId}")
	public CidadeModel atualizar(
		@ApiParam(value = "ID de uma cidade", example = "1") 
		@PathVariable Long cidadeId, 
		
		@ApiParam(name = "corpo", value = "Representação de uma cidade com os novos dados") 
		@RequestBody @Valid CidadeInput cidadeInput) {
		try {
			Cidade cidadeAtual = cadastroCidade.buscarOuFalhar(cidadeId);
			cidadeInputDisassembler.copyToDomainObject(cidadeInput, cidadeAtual);
			return cidadeModelAssembler.toModel(cadastroCidade.salvar(cidadeAtual));
		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@ApiOperation("Exclui uma cidade por ID")
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Cidade excluída"),
		@ApiResponse(responseCode = "404", description = "Cidade não encotrada", 
		content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Problem.class)))
	})
	@DeleteMapping("/{cidadeId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(
		@ApiParam(value = "ID de uma cidade", example = "1")  
		@PathVariable Long cidadeId) {
		cadastroCidade.excluir(cidadeId);
	}
	
	

}
