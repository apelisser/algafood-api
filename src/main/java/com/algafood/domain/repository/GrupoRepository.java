package com.algafood.domain.repository;

import org.springframework.stereotype.Repository;

import com.algafood.domain.model.Grupo;

@Repository
public interface GrupoRepository extends CustomJpaRepository<Grupo, Long>{

}
