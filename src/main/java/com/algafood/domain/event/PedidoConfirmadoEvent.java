package com.algafood.domain.event;

import com.algafood.domain.model.Pedido;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
public class PedidoConfirmadoEvent {

	private Pedido pedido;
	
}
