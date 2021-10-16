package com.algafood.api.model.mixin;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import com.algafood.domain.model.Cozinha;
import com.algafood.domain.model.Endereco;
import com.algafood.domain.model.FormaPagamento;
import com.algafood.domain.model.Produto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class RestauranteMixin {
	
//	@JsonIgnoreProperties({"nome"})
	@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "nome" }, allowGetters = true)
	private Cozinha cozinha;

	@JsonIgnore
	private Endereco endereco;

	@JsonIgnore
	private OffsetDateTime dataCadastro;

	@JsonIgnore
	private OffsetDateTime dataAtualizacao;

	@JsonIgnore
	private List<Produto> produtos = new ArrayList<>();

	@JsonIgnore
	private List<FormaPagamento> formasPagamento = new ArrayList<>();

}
