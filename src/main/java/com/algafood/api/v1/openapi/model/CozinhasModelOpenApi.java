package com.algafood.api.v1.openapi.model;


import java.util.List;

import org.springframework.hateoas.Links;

import com.algafood.api.v1.model.CozinhaModel;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@ApiModel("CozinhasModel")
@Setter
@Getter
public class CozinhasModelOpenApi {
	
	private CozinhasEmbeddedModelOpenApi _embedded;
	private Links _links;
	private PageModelOpenApi page;
	
	@ApiModel("CozinhasEmbeddedModel")
	@Data
	private class CozinhasEmbeddedModelOpenApi {
		
		private List<CozinhaModel> cozinha;
		
	}
}
