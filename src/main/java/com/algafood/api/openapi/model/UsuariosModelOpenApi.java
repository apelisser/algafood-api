package com.algafood.api.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.algafood.api.model.UsuarioModel;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("UsuariosModel")
@Data
public class UsuariosModelOpenApi {

	private UsuariosEmbeddedModelOpenApi _embedded;
	private Links _links;
	
	@ApiModel("UsuariosEmbeddedModel")
	@Data
	private class UsuariosEmbeddedModelOpenApi {
		
		private List<UsuarioModel> usuarios;
		
	}
	
}
