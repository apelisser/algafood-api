package com.algafood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algafood.api.v1.AlgaLinks;
import com.algafood.api.v1.controller.PedidoController;
import com.algafood.api.v1.model.PedidoResumoModel;
import com.algafood.core.security.AlgaSecurity;
import com.algafood.domain.model.Pedido;

@Component
public class PedidoResumoModelAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoResumoModel> {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AlgaLinks algaLinks;
	
	@Autowired
	private AlgaSecurity algaSecurity;
	
	public PedidoResumoModelAssembler() {
		super(PedidoController.class, PedidoResumoModel.class);
	}
	
	public PedidoResumoModel toModel(Pedido pedido) {
		PedidoResumoModel pedidoResumoModel = createModelWithId(pedido.getCodigo(), pedido);
		
		modelMapper.map(pedido, pedidoResumoModel);
		
		if (algaSecurity.podePesquisarPedidos()) {
			pedidoResumoModel.add(algaLinks.linkToPedidos("pedidos"));
		}
		
		if (algaSecurity.podeConsultarRestaurantes()) {
			pedidoResumoModel.getRestaurante().add(algaLinks.linkToRestaurante(
					pedidoResumoModel.getRestaurante().getId()));
		}
		
		if (algaSecurity.podeConsultarUsuarioGruposPermissoes()) {
			pedidoResumoModel.getCliente().add(algaLinks.linkToCliente(
					pedidoResumoModel.getCliente().getId()));
		}
		
		return pedidoResumoModel;
	}
	
}
