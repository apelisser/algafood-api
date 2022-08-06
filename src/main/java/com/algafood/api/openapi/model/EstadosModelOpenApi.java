package com.algafood.api.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.algafood.api.model.EstadoModel;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("CidadesModel")
@Data
public class EstadosModelOpenApi {

	private EstadosEmbeddedModelOpenApi _embedded;
	private Links _links;
	
	@ApiModel("EstadosEmbeddedModel")
	@Data
	private class EstadosEmbeddedModelOpenApi {
		
		private List<EstadoModel> estados;
		
	}
	
}
