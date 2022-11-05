package com.algafood.api.v1.openapi.controller;

import org.springframework.http.ResponseEntity;

public interface FluxoPedidoControllerOpenApi {

	public ResponseEntity<Void> confirmar(String codigoPedido);

	public ResponseEntity<Void> cancelar(String codigoPedido);

	public ResponseEntity<Void> entregar(String codigoPedido);

}
