package com.algafood.api.v1.openapi.controller;

import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.algafood.api.v1.model.RestauranteApenasNomeModel;
import com.algafood.api.v1.model.RestauranteBasicoModel;
import com.algafood.api.v1.model.RestauranteModel;
import com.algafood.api.v1.model.input.RestauranteInput;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "security_auth")
public interface RestauranteControllerOpenApi {

	@Operation(parameters = {
			@Parameter(name = "projecao",
				description = "Nome da projeção",
				example = "apenas-nome",
				in = ParameterIn.QUERY,
				required = false				
			)
	})
	public CollectionModel<RestauranteBasicoModel> listar();

	@Operation(hidden = true)
	public CollectionModel<RestauranteApenasNomeModel> listarApenasNomes();

	public RestauranteModel buscar(Long restauranteId);

	public RestauranteModel adicionar(RestauranteInput restauranteInput);

	public RestauranteModel atualizar(Long restauranteId, RestauranteInput restauranteInput);

	public void remover(Long restauranteId);

	public ResponseEntity<Void> ativar(Long restauranteId);

	public ResponseEntity<Void> inativar(Long restauranteId);

	public ResponseEntity<Void> abrir(Long restauranteId);

	public ResponseEntity<Void> fechar(Long restauranteId);

	public void ativarMultiplos(List<Long> restauranteIds);

	public void inativarMultiplos(List<Long> restauranteIds);

}
