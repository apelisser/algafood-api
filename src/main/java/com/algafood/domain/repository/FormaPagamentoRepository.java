package com.algafood.domain.repository;

import java.util.List;

import com.algafood.domain.model.FormaPagamento;

public interface FormaPagamentoRepository {

	List<FormaPagamento> todas();

	FormaPagamento porId(Long id);

	FormaPagamento adicionar(FormaPagamento formaPagamento);

	void remover(FormaPagamento formaPagamento);

}
