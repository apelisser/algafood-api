package com.algafood.domain.repository;

import org.springframework.stereotype.Repository;

import com.algafood.domain.model.Estado;

@Repository
public interface EstadoRepository extends CustomJpaRepository<Estado, Long> {

}
