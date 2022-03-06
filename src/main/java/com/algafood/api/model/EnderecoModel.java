package com.algafood.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EnderecoModel {
	
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
