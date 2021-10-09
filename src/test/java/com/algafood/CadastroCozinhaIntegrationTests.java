package com.algafood;

import static org.assertj.core.api.Assertions.assertThat;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.algafood.domain.exception.EntidadeEmUsoException;
import com.algafood.domain.model.Cozinha;
import com.algafood.domain.service.CadastroCozinhaService;

@SpringBootTest
class CadastroCozinhaIntegrationTests {

	@Autowired
	private CadastroCozinhaService cadastroCozinha;

	@Test
	public void deveAtribuirId_QuandoCadastrarCozinhaComDadosCorretos() {
		// cenário
		Cozinha novaCozinha = new Cozinha();
		novaCozinha.setNome("Chinesa");

		// ação
		novaCozinha = cadastroCozinha.salvar(novaCozinha);

		// validação
		assertThat(novaCozinha).isNotNull();
		assertThat(novaCozinha.getId()).isNotNull();
	}

	@Test
	public void deveFalhar_QuandoCadastrarCozinhaSemNome() {
		// cenário
		Cozinha novaCozinha = new Cozinha();
		novaCozinha.setNome(null);

		// ação
		ConstraintViolationException exception = 
				Assertions.assertThrows(ConstraintViolationException.class, () -> {					
					cadastroCozinha.salvar(novaCozinha);
				});
		
		assertThat(exception).isNotNull();
		
	}
	
	@Test
    public void deveFalhar_QuandoExcluirCozinhaEmUso() {
		EntidadeEmUsoException exception = 
				Assertions.assertThrows(EntidadeEmUsoException.class, () -> {
					cadastroCozinha.excluir(1L);
				});
		
		assertThat(exception).isNotNull();
    }
	
	@Test
    public void deveFalhar_QuandoExcluirCozinhaInexistente() {
		CozinhaNaoEncontradaException exception = 
				Assertions.assertThrows(CozinhaNaoEncontradaException.class, () -> {
					cadastroCozinha.excluir(100L);
				});
		
		assertThat(exception).isNotNull();
    }

}
