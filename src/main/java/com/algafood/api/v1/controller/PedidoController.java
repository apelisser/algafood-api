package com.algafood.api.v1.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algafood.api.v1.assembler.PedidoInputDisassembler;
import com.algafood.api.v1.assembler.PedidoModelAssembler;
import com.algafood.api.v1.assembler.PedidoResumoModelAssembler;
import com.algafood.api.v1.model.PedidoModel;
import com.algafood.api.v1.model.PedidoResumoModel;
import com.algafood.api.v1.model.input.PedidoInput;
import com.algafood.api.v1.openapi.controller.PedidoControllerOpenApi;
import com.algafood.core.data.PageWrapper;
import com.algafood.core.data.PageableTranslator;
import com.algafood.core.security.AlgaSecurity;
import com.algafood.core.security.CheckSecurity;
import com.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algafood.domain.exception.NegocioException;
import com.algafood.domain.filter.PedidoFilter;
import com.algafood.domain.model.Pedido;
import com.algafood.domain.model.Usuario;
import com.algafood.domain.repository.PedidoRepository;
import com.algafood.domain.service.EmissaoPedidoService;
import com.algafood.infrastructure.repository.spec.PedidoSpecs;
import com.google.common.collect.ImmutableMap;

@RestController
@RequestMapping(value = "/v1/pedidos", produces = MediaType.APPLICATION_JSON_VALUE)
public class PedidoController implements PedidoControllerOpenApi {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private EmissaoPedidoService emissaoPedido;
	
	@Autowired
	private PedidoModelAssembler pedidoModelAssembler;
	
	@Autowired
	private PedidoResumoModelAssembler pedidoResumoModelAssembler;
	
	@Autowired
	private PedidoInputDisassembler pedidoInputDisassembler;
	
	@Autowired 
	private PagedResourcesAssembler<Pedido> pagedResourcesAssembler;
	
	@Autowired
	private AlgaSecurity algaSecurity;
	
	@CheckSecurity.Pedidos.PodePesquisar
	@GetMapping
	public PagedModel<PedidoResumoModel> pesquisar(PedidoFilter filtro, 
			@PageableDefault(size = 10) Pageable pageable) {
		
		Pageable pageableTraduzido = traduzirPageable(pageable);
		
		Page<Pedido> pedidosPage = pedidoRepository.findAll(
				PedidoSpecs.usandoFiltro(filtro), pageableTraduzido);
		
		pedidosPage = new PageWrapper<>(pedidosPage, pageable);
		
		PagedModel<PedidoResumoModel> pedidosPagedModel = pagedResourcesAssembler
				.toModel(pedidosPage, pedidoResumoModelAssembler);
				
		return pedidosPagedModel;
	}
	
	@CheckSecurity.Pedidos.PodeBuscar
	@GetMapping("/{codigoPedido}")
	public PedidoModel buscar(@PathVariable String codigoPedido) {
		Pedido pedido = emissaoPedido.buscarOuFalhar(codigoPedido);
		
		return pedidoModelAssembler.toModel(pedido);
	}
	
	@CheckSecurity.Pedidos.PodeCriar
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PedidoModel adicionar(@RequestBody @Valid PedidoInput pedidoInput) {
		try {
			Pedido novoPedido = pedidoInputDisassembler.toDomainObject(pedidoInput);
			
			novoPedido.setCliente(new Usuario());
			novoPedido.getCliente().setId(algaSecurity.getUsuarioId());
			
			novoPedido = emissaoPedido.emitir(novoPedido);
			
			return pedidoModelAssembler.toModel(novoPedido);
			
		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}
	
	private Pageable traduzirPageable(Pageable apiPageable) {
		var mapeamento = ImmutableMap.of(
					"codigo", "codigo",
					"subtotal", "subtotal",
					"restaurante.nome", "restaurante.nome",
					"cliente.nome", "cliente.nome",
					"valorTotal", "valorTotal"
				);
		
		return PageableTranslator.translate(apiPageable, mapeamento);
	}
	 
}
