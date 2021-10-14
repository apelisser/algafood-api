package com.algafood;

import java.math.BigDecimal;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import com.algafood.domain.model.Cozinha;
import com.algafood.domain.model.Restaurante;
import com.algafood.domain.repository.CozinhaRepository;
import com.algafood.domain.repository.RestauranteRepository;
import com.algafood.util.DatabaseCleaner;
import com.algafood.util.ResourceUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class CadastroRestauranteIT {

	private static final String DADOS_INVALIDOS_PROBLEM_TITLE = "Dados inválidos";
	private static final String VIOLACAO_DE_REGRA_DE_NEGOCIO_PROBLEM_TYPE = "Violação de regra de negócio";
	private static final int RESTAURANTE_ID_INEXISTENTE = 100;
	
	@LocalServerPort
	private int port;

	@Autowired
	private DatabaseCleaner databaseCleaner;

	@Autowired
	private RestauranteRepository restauranteRepository;

	@Autowired
	private CozinhaRepository cozinhaRepository;


	private Restaurante burgerTopRestaurante;
	private int qtdeRestauranteCadastrado;

	private String jsonRestauranteCorreto;
	private String jsonRestauranteSemTaxaFrete;
	private String jsonRestauranteSemCozinha;
	private String jsonRestauranteCozinhaInexistente;
	

	// é executado antes de cada teste(abaixo) ser executado
		@BeforeEach
		public void setUp() {
			RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
			RestAssured.port = port;
			RestAssured.basePath = "/restaurantes";

			this.jsonRestauranteCorreto = ResourceUtils.getContentFromResource(
	                "/json/correto/restaurante-new-york-barbecue.json");

			this.jsonRestauranteSemTaxaFrete = ResourceUtils.getContentFromResource(
					"/json/incorreto/restaurante-new-york-barbecue-sem-frete.json");

			this.jsonRestauranteSemCozinha = ResourceUtils.getContentFromResource(
					"/json/incorreto/restaurante-new-york-barbecue-sem-cozinha.json");

			this.jsonRestauranteCozinhaInexistente = ResourceUtils.getContentFromResource(
					"/json/incorreto/restaurante-new-york-barbecue-com-cozinha-inexistente.json");
			
			databaseCleaner.clearTables();
			prepararDados();
		}

		@Test
		public void deveRetornarStatus200_QuandoConsultarRestaurantes() {
			RestAssured.given()
				.accept(ContentType.JSON)
			.when()
				.get()
			.then()
				.statusCode(HttpStatus.OK.value());
		}

		@Test
		public void deveRetornarStatus201_QuandoCadastrarRestaurante() {
			RestAssured.given()
				.body(this.jsonRestauranteCorreto)
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
			.when()
				.post()
			.then()
				.statusCode(HttpStatus.CREATED.value());
		}

		@Test
		public void deveRetornarStatus400_QuandoCadastrarRestauranteSemTaxaFrete() {
			RestAssured.given()
			.body(this.jsonRestauranteSemTaxaFrete)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.BAD_REQUEST.value())
			.body("title", Matchers.equalTo(DADOS_INVALIDOS_PROBLEM_TITLE));
		}

		@Test
		public void deveRetornarStatus400_QuandoCadastrarRestauranteSemCozinha() {
			RestAssured.given()
				.body(this.jsonRestauranteSemCozinha)
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
			.when()
				.post()
			.then()
				.statusCode(HttpStatus.BAD_REQUEST.value())
				.body("title", Matchers.equalTo(DADOS_INVALIDOS_PROBLEM_TITLE));
		}

		@Test
		public void deveRetornarStatus400_QuandoCadastrarRestauranteComCozinhaInexistente() {
			RestAssured.given()
				.body(this.jsonRestauranteCozinhaInexistente)
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
			.when()
				.post()
			.then()
				.statusCode(HttpStatus.BAD_REQUEST.value())
				.body("title", Matchers.equalTo(VIOLACAO_DE_REGRA_DE_NEGOCIO_PROBLEM_TYPE));
		}

		@Test
		public void deveConterTodasOsRestaurantesCadastrados_QuandoConsultarRestaurantes() {		
			RestAssured.given()
				.accept(ContentType.JSON)
			.when()
				.get()
			.then()
				.body("", Matchers.hasSize(this.qtdeRestauranteCadastrado));
		}
		
		@Test
		public void deveRetornarRespostaEStatusCorretos_QuandoConsultarRestauranteExistente() {
			RestAssured.given()
				.pathParam("restauranteId", this.burgerTopRestaurante.getId())
				.accept(ContentType.JSON)
			.when()
				.get("/{restauranteId}")
			.then()
				.statusCode(HttpStatus.OK.value())
				.body("nome", Matchers.equalTo(this.burgerTopRestaurante.getNome()));
		}
		
		@Test
		public void deveRetornarStatus404_QuandoConsultarRestauranteInexistente() {
			RestAssured.given()
				.pathParam("restauranteId", RESTAURANTE_ID_INEXISTENTE)
				.accept(ContentType.JSON)
			.when()
				.get("/{restauranteId}")
			.then()
				.statusCode(HttpStatus.NOT_FOUND.value());
		}
		
		private void prepararDados() {
			this.qtdeRestauranteCadastrado = 0;
			
			Cozinha cozinha1 = new Cozinha();
			cozinha1.setNome("Brasileira");
			cozinhaRepository.save(cozinha1);

			Cozinha cozinha2 = new Cozinha();
			cozinha2.setNome("Americana");
			cozinhaRepository.save(cozinha2);

			Restaurante restaurante1 = new Restaurante();
			restaurante1.setNome("Hip-Hop Food - frete grátis");
			restaurante1.setTaxaFrete(BigDecimal.ZERO);
			restaurante1.setCozinha(cozinha1);
			restauranteRepository.save(restaurante1);
			this.qtdeRestauranteCadastrado++;
			this.burgerTopRestaurante = restaurante1;

			Restaurante restaurante2 = new Restaurante();
			restaurante2.setNome("Novo no Pedaço");
			restaurante2.setTaxaFrete(BigDecimal.valueOf(10));
			restaurante2.setCozinha(cozinha2);
			restauranteRepository.save(restaurante2);
			this.qtdeRestauranteCadastrado++;
		}

}
