package com.algafood.api.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.algafood.api.model.GrupoModel;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("GruposModel")
@Data
public class GruposModelOpenApi {

	private GruposEmbeddedModelOpenApi _embedded;
	private Links _links;
	
	@ApiModel("GruposEmbeddedModel")
	@Data
	private class GruposEmbeddedModelOpenApi {
		
		private List<GrupoModel> grupos;
		
	}
	
}
