package com.algafood.domain.repository;

import org.springframework.stereotype.Repository;

import com.algafood.domain.model.Permissao;

@Repository
public interface PermissaoRepository extends CustomJpaRepository<Permissao, Long> {

}
