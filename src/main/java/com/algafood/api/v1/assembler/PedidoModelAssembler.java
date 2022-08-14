package com.algafood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algafood.api.v1.AlgaLinks;
import com.algafood.api.v1.controller.PedidoController;
import com.algafood.api.v1.model.PedidoModel;
import com.algafood.domain.model.Pedido;

@Component
public class PedidoModelAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoModel> {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AlgaLinks algaLinks;

	public PedidoModelAssembler() {
		super(PedidoController.class, PedidoModel.class);
	}
		
	public PedidoModel toModel(Pedido pedido) {
		PedidoModel pedidoModel = createModelWithId(pedido.getCodigo(), pedido);
		
		modelMapper.map(pedido, pedidoModel);
		
		if (pedido.podeSerConfirmado()) {
			pedidoModel.add(algaLinks.linkToConfirmacaoPedido(pedido.getCodigo(), "confirmar"));
		}
		
		if (pedido.podeSerEntregue()) {
			pedidoModel.add(algaLinks.linkToEntregaPedido(pedido.getCodigo(), "entregar"));
		}

		if (pedido.podeSerCancelado()) {
			pedidoModel.add(algaLinks.linkToCancelamentoPedido(pedido.getCodigo(), "cancelar"));
		}
		
		pedidoModel.add(algaLinks.linkToPedidos("pedidos"));
		
		pedidoModel.getRestaurante().add(algaLinks.linkToRestaurante(pedido.getRestaurante().getId()));
		
		pedidoModel.getCliente().add(algaLinks.linkToCliente(pedido.getCliente().getId()));
		
		// Passamos null no segundo argumento, porque é indiferente para a
        // construção da URL do recurso de forma de pagamento
		pedidoModel.getFormaPagamento().add(algaLinks.linkToFormaPagamento(pedido.getFormaPagamento().getId()));
				
		pedidoModel.getEnderecoEntrega().getCidade().add(algaLinks.linkToCidade(pedido.getEnderecoEntrega().getCidade().getId()));
				
		pedidoModel.getItens().forEach(item -> 
			item.add(algaLinks.linkToRestauranteProduto(
					pedidoModel.getRestaurante().getId(), item.getProdutoId(), "produto"))
		);
		
		return pedidoModel;
	}	
}
