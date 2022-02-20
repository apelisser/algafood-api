package com.algafood.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

@ApiModel("Problema")
@JsonInclude(Include.NON_NULL)
@Getter
@Builder
public class Problem {
		
	@ApiModelProperty(example = "400", position = 1)
	private Integer status;
	
	@ApiModelProperty(example = "2022-02-12T14:42:16.811764133Z", position = 10)
	private OffsetDateTime timestamp;

	@ApiModelProperty(example = "https://algafood.com.br/mensagem-incompreensivel", position = 20)
	private String type;
	
	@ApiModelProperty(example = "Mensagem incompreensível", position = 30)
	private String title;
	
	@ApiModelProperty(example = "O corpo da requisição está inválido. Verifique erro de sintaxe", position = 40)
	private String detail;
	
	@ApiModelProperty(example = "O corpo da requisição está inválido. Verifique erro de sintaxe", position = 50)
	private String userMessage;

	@ApiModelProperty(value = "Objetos ou campos que geraram o erro", position = 60)
	private List<Object> objects;
	
	@ApiModel("ObjetoProblema")
	@Getter
	@Builder
	public static class Object {
		@ApiModelProperty(example = "Preço")
		private String name;
		
		@ApiModelProperty(example = "O preço é obrigatório")
		private String userMessage;
	}
	
}
