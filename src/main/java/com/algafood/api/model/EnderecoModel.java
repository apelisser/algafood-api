package com.algafood.api.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "enderecos")
@Setter
@Getter
public class EnderecoModel extends RepresentationModel<EnderecoModel> {
	
	@ApiModelProperty(example = "19003-000")
	private String cep;
	
	@ApiModelProperty(example = "Rua Pedreira Louca")
	private String logradouro;
	
	@ApiModelProperty(example = "750")
	private String numero;
	
	@ApiModelProperty(example = "Próx. ao Banco da Terra")
	private String complemento;
	
	@ApiModelProperty(example = "Vila Matilde")
	private String bairro;
	
	private CidadeResumoModel cidade;
}
