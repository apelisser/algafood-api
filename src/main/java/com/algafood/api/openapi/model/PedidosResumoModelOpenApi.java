package com.algafood.api.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.algafood.api.model.PedidoResumoModel;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@ApiModel("PedidosResumoModel")
@Setter
@Getter
public class PedidosResumoModelOpenApi {
	
	private PedidosResumoEmbeddedModelOpenApi _embedded;
	private Links _links;
	private PageModelOpenApi page;
	
	@ApiModel("PedidosResumoEmbeddedModel")
	@Data
	private class PedidosResumoEmbeddedModelOpenApi {
		
		private List<PedidoResumoModel> pedidos;
		
	}
}
