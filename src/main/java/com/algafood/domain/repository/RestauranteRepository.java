package com.algafood.domain.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.algafood.domain.model.Restaurante;

@Repository
public interface RestauranteRepository extends CustomJpaRepository<Restaurante, Long>, RestauranteRepositoryQueries, JpaSpecificationExecutor<Restaurante> {

	// o prefixo pode ser:
	// (find, read, get, query) -> todos tem a mesma função

	// query para fazer somente um select no banco
	@Query("from Restaurante r join fetch r.cozinha")
	List<Restaurante> findAll();
	
	List<Restaurante> queryByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);

//	List<Restaurante> findByNomeContainingAndCozinhaId(String nome, Long cozinhaId); // função abaixo com mesmo resultado

//	@Query("from Restaurante where nome like %:nome% and cozinha.id = :id") // query implementada em src/main/resources/META-INF/orm.xml
	List<Restaurante> consultarPorNome(String nome, @Param("id") Long cozinhaId);

	Optional<Restaurante> findFirstQualquerCoisaAquiByNomeContaining(String nome);

	List<Restaurante> findTop2ByNomeContaining(String nome);

	int countByCozinhaId(Long cozinhaId);

}
