package com.algafood.core.openapi.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("Pageable")
public class PageableModelOpenApi {

	@Schema(example = "0", description = "Número da página (começa com 0)")
	private int page;
	
	@Schema(example = "10", description = "Quantidade de elementos por página")
	private int size;
	
	@Schema(example = "nome,asc", description = "Nome da propriedade para ordenação")
	private List<String> sort;
	
}
