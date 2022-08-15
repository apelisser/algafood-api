package com.algafood.api.v2.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
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

import com.algafood.api.ResourceUriHelper;
import com.algafood.api.v2.assembler.CidadeInputDisassemblerV2;
import com.algafood.api.v2.assembler.CidadeModelAssemblerV2;
import com.algafood.api.v2.model.CidadeModelV2;
import com.algafood.api.v2.model.input.CidadeInputV2;
import com.algafood.domain.exception.EstadoNaoEncontradoException;
import com.algafood.domain.exception.NegocioException;
import com.algafood.domain.model.Cidade;
import com.algafood.domain.repository.CidadeRepository;
import com.algafood.domain.service.CadastroCidadeService;

@RestController
@RequestMapping(path = "/v2/cidades", produces = MediaType.APPLICATION_JSON_VALUE)
public class CidadeControllerV2 {

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private CadastroCidadeService cadastroCidade;
	
	@Autowired
	private CidadeInputDisassemblerV2 cidadeInputDisassembler;
	
	@Autowired
	private CidadeModelAssemblerV2 cidadeModelAssembler;


	@GetMapping
	public CollectionModel<CidadeModelV2> listar() {
		List<Cidade> todasCidades = cidadeRepository.findAll();
		
		return cidadeModelAssembler.toCollectionModel(todasCidades);	
	}
	
	@GetMapping("/{cidadeId}")
	public CidadeModelV2 buscar(
		@PathVariable Long cidadeId) {
		Cidade cidade = cadastroCidade.buscarOuFalhar(cidadeId);
		
		return cidadeModelAssembler.toModel(cidade);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CidadeModelV2 adicionar(@RequestBody @Valid CidadeInputV2 CidadeInputV2) {
		try {
			Cidade cidade = cidadeInputDisassembler.toDomainObject(CidadeInputV2);
			
			cidade = cadastroCidade.salvar(cidade);
			CidadeModelV2 CidadeModelV2 = cidadeModelAssembler.toModel(cidade);
			
			ResourceUriHelper.addUriInResponseHeader(CidadeModelV2.getIdCidade());
			
			return CidadeModelV2;
		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@PutMapping("/{cidadeId}")
	public CidadeModelV2 atualizar(
		@PathVariable Long cidadeId, 
		@RequestBody @Valid CidadeInputV2 CidadeInputV2) {
		try {
			Cidade cidadeAtual = cadastroCidade.buscarOuFalhar(cidadeId);
			cidadeInputDisassembler.copyToDomainObject(CidadeInputV2, cidadeAtual);
			return cidadeModelAssembler.toModel(cadastroCidade.salvar(cidadeAtual));
		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@DeleteMapping("/{cidadeId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(
		@PathVariable Long cidadeId) {
		cadastroCidade.excluir(cidadeId);
	}

}
