package com.algafood.domain.repository;

import java.time.OffsetDateTime;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.algafood.domain.model.FormaPagamento;

@Repository
public interface FormaPagamentoRepository extends CustomJpaRepository<FormaPagamento, Long> {

	@Query("select max(dataAtualizacao) from FormaPagamento")
	OffsetDateTime getDataAtualizacao();
	
}
