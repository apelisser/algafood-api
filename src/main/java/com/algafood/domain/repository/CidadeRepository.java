package com.algafood.domain.repository;

import org.springframework.stereotype.Repository;

import com.algafood.domain.model.Cidade;

@Repository
public interface CidadeRepository extends CustomJpaRepository<Cidade, Long>{

}
