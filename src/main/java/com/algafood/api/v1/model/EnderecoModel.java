package com.algafood.api.v1.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "enderecos")
@Setter
@Getter
public class EnderecoModel extends RepresentationModel<EnderecoModel> {
	
	@Schema(example = "19000-123")
	private String cep;
	
	@Schema(example = "Rua Quatorze de Dezembro")
	private String logradouro;
	
	@Schema(example = "999")
	private String numero;
	
	@Schema(example = "Apto 07")
	private String complemento;
	
	@Schema(example = "Centro")
	private String bairro;
	
	private CidadeResumoModel cidade;
}
