package com.algafood.domain.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.algafood.domain.model.Restaurante;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long>, RestauranteRepositoryQueries {

	// o prefixo pode ser:
	// (find, read, get, query) -> todos tem a mesma função

	List<Restaurante> queryByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);

	List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal);

//	List<Restaurante> findByNomeContainingAndCozinhaId(String nome, Long cozinhaId); // função abaixo com mesmo resultado

//	@Query("from Restaurante where nome like %:nome% and cozinha.id = :id") // query implementada em src/main/resources/META-INF/orm.xml
	List<Restaurante> consultarPorNome(String nome, @Param("id") Long cozinhaId);

	Optional<Restaurante> findFirstQualquerCoisaAquiByNomeContaining(String nome);

	List<Restaurante> findTop2ByNomeContaining(String nome);

	int countByCozinhaId(Long cozinhaId);

}
