package com.algafood;

//import static org.hamcrest.CoreMatchers.equalTo;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import com.algafood.domain.model.Cozinha;
import com.algafood.domain.repository.CozinhaRepository;
import com.algafood.util.DatabaseCleaner;
import com.algafood.util.ResourceUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
class CadastroCozinhaIT {

	private static final int COZINHA_ID_INEXISTENTE = 100;

	@LocalServerPort
	private int port;
	
	@Autowired
	private DatabaseCleaner databaseCleaner;
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	private Cozinha cozinhaAmericana;
	private int qtdeCozinhaCadastrada;
	private String jsonCorretoCadastroCozinha;
	
	// é executado antes de cada teste(abaixo) ser executado
	@BeforeEach
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/cozinhas";
		
		this.jsonCorretoCadastroCozinha = ResourceUtils.getContentFromResource("/json/correto/cadastro-cozinha-test.json");
		databaseCleaner.clearTables();
		prepararDados();
	}
	
	@Test
	public void deveRetornarStatus200_QuandoConsultarCozinhas() {
		RestAssured.given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void deveConterTodasAsCozinhasCadastradas_QuandoConsultarCozinhas() {		
		RestAssured.given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.body("", Matchers.hasSize(this.qtdeCozinhaCadastrada));
	}
	
	@Test
	public void deveRetornarStatus201_QuandoCadastrarCozinha() {
		RestAssured.given()
			.body(this.jsonCorretoCadastroCozinha)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value());
	}
	
	@Test
	public void deveRetornarRespostaEStatusCorretos_QuandoConsultarCozinhaExistente() {
		RestAssured.given()
			.pathParam("cozinhaId", this.cozinhaAmericana.getId())
			.accept(ContentType.JSON)
		.when()
			.get("/{cozinhaId}")
		.then()
			.statusCode(HttpStatus.OK.value())
			.body("nome", Matchers.equalTo(this.cozinhaAmericana.getNome()));
	}
	
	@Test
	public void deveRetornarStatus404_QuandoConsultarCozinhaInexistente() {
		RestAssured.given()
			.pathParam("cozinhaId", COZINHA_ID_INEXISTENTE)
			.accept(ContentType.JSON)
		.when()
			.get("/{cozinhaId}")
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value());
	}
	
	private void prepararDados() {
		this.qtdeCozinhaCadastrada = 0;
		
		Cozinha cozinha1 = new Cozinha();
		cozinha1.setNome("Tailandesa");
		cozinhaRepository.save(cozinha1);
		this.qtdeCozinhaCadastrada++;
		
		Cozinha cozinha2 = new Cozinha();
		cozinha2.setNome("Americana");
		cozinhaRepository.save(cozinha2);
		this.cozinhaAmericana = cozinha2;
		this.qtdeCozinhaCadastrada++;
		
		Cozinha cozinha3 = new Cozinha();
		cozinha3.setNome("Japonesa");
		cozinhaRepository.save(cozinha3);
		this.qtdeCozinhaCadastrada++;
	}
	
	
	// =================================================================================================
	// ========================== TESTES DE INTEGRAÇÃO =================================================
	// =================================================================================================
	
/*	
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
*/

}
