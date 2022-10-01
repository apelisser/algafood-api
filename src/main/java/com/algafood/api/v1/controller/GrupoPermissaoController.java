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
import com.algafood.api.v1.assembler.PermissaoModelAssembler;
import com.algafood.api.v1.model.PermissaoModel;
import com.algafood.api.v1.openapi.controller.GrupoPermissaoControllerOpenApi;
import com.algafood.core.security.CheckSecurity;
import com.algafood.domain.model.Grupo;
import com.algafood.domain.service.CadastroGrupoService;

@RestController
@RequestMapping(value = "/v1/grupos/{grupoId}/permissoes", produces = MediaType.APPLICATION_JSON_VALUE)
public class GrupoPermissaoController implements GrupoPermissaoControllerOpenApi {

	
	@Autowired
	private CadastroGrupoService cadastroGrupo;
	
	@Autowired
	private PermissaoModelAssembler permissaoModelAssembler;
	
	@Autowired
	private AlgaLinks algaLinks;
	
	@CheckSecurity.UsuarioGruposPermissoes.PodeConsultar
	@Override
	@GetMapping
	public CollectionModel<PermissaoModel> listar(@PathVariable Long grupoId) {
		Grupo grupo = cadastroGrupo.buscarOuFalhar(grupoId);
		
		CollectionModel<PermissaoModel> permissoesModel = 
				permissaoModelAssembler.toCollectionModel(grupo.getPermissoes())
				.removeLinks()
				.add(algaLinks.linkToGrupoPermissoes(grupoId))
				.add(algaLinks.linkToGrupoPermissaoAssociacao(grupoId, "associar"));
		
		permissoesModel.getContent().forEach(permissaoModel -> {
			permissaoModel.add(algaLinks.linkToGrupoPermissaoDesassociacao(
					grupoId, permissaoModel.getId(), "desassociar"));
		});
		
		return permissoesModel;
	}
	
	@CheckSecurity.UsuarioGruposPermissoes.PodeEditar
	@Override
	@PutMapping("/{permissaoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> associar(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
		cadastroGrupo.associarPermissao(grupoId, permissaoId);
		
		return ResponseEntity.noContent().build();
	}
	
	@CheckSecurity.UsuarioGruposPermissoes.PodeEditar
	@Override
	@DeleteMapping("/{permissaoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void>	desassociar(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
		cadastroGrupo.desassociarPermissao(grupoId, permissaoId);
		
		return ResponseEntity.noContent().build();
	}
}
