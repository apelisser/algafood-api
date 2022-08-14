package com.algafood.api.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algafood.api.v1.AlgaLinks;
import com.algafood.api.v1.assembler.UsuarioModelAssembler;
import com.algafood.api.v1.model.UsuarioModel;
import com.algafood.api.v1.openapi.controller.RestauranteUsuarioResponsavelControllerOpenApi;
import com.algafood.domain.model.Restaurante;
import com.algafood.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping(value = "/restaurantes/{restauranteId}/responsaveis", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteUsuarioResponsavelController implements RestauranteUsuarioResponsavelControllerOpenApi {

	@Autowired
	private CadastroRestauranteService cadastroRestaurante;
	
	@Autowired
	private UsuarioModelAssembler usuarioModelAssembler;
	
	@Autowired
	private AlgaLinks algaLinks;
	
	@GetMapping
	public CollectionModel<UsuarioModel> listar(@PathVariable Long restauranteId) {
		Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId);
		
		CollectionModel<UsuarioModel> usuariosResponsaveisModel = usuarioModelAssembler.toCollectionModel(restaurante.getResponsaveis())
				.removeLinks()
				.add(algaLinks.linkToResponsaveisRestaurante(restauranteId))
				.add(algaLinks.linkToRestauranteResponsavelAssociacao(restauranteId, "associar"));
		
		usuariosResponsaveisModel.getContent().forEach(usuarioResponsavelModel -> {
			usuarioResponsavelModel.add(algaLinks.linkToRestauranteResponsavelDesassociacao(
					restaurante.getId(), usuarioResponsavelModel.getId(), "desassociar"));
		});
		
		return usuariosResponsaveisModel;
	}
	
	@PutMapping("/{usuarioId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> associar(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
		cadastroRestaurante.associarResponsavel(restauranteId, usuarioId);		
		
		return ResponseEntity.noContent().build();		
	}
	
	@DeleteMapping("/{usuarioId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> desassociar(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
		cadastroRestaurante.desassociarResponsavel(restauranteId, usuarioId);				
		
		return ResponseEntity.noContent().build();	
	}
	
}
