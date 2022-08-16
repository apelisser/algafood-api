package com.algafood.core.springfox;

import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLStreamHandler;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Links;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.algafood.api.exceptionhandler.Problem;
import com.algafood.api.v1.model.CidadeModel;
import com.algafood.api.v1.model.CozinhaModel;
import com.algafood.api.v1.model.EstadoModel;
import com.algafood.api.v1.model.FormaPagamentoModel;
import com.algafood.api.v1.model.GrupoModel;
import com.algafood.api.v1.model.PedidoResumoModel;
import com.algafood.api.v1.model.PermissaoModel;
import com.algafood.api.v1.model.ProdutoModel;
import com.algafood.api.v1.model.RestauranteBasicoModel;
import com.algafood.api.v1.model.UsuarioModel;
import com.algafood.api.v1.openapi.model.CidadesModelOpenApi;
import com.algafood.api.v1.openapi.model.CozinhasModelOpenApi;
import com.algafood.api.v1.openapi.model.EstadosModelOpenApi;
import com.algafood.api.v1.openapi.model.FormasPagamentoModelOpenApi;
import com.algafood.api.v1.openapi.model.GruposModelOpenApi;
import com.algafood.api.v1.openapi.model.LinksModelOpenApi;
import com.algafood.api.v1.openapi.model.PageableModelOpenApi;
import com.algafood.api.v1.openapi.model.PedidosResumoModelOpenApi;
import com.algafood.api.v1.openapi.model.PermissoesModelOpenApi;
import com.algafood.api.v1.openapi.model.ProdutosModelOpenApi;
import com.algafood.api.v1.openapi.model.RestaurantesBasicoModelOpenApi;
import com.algafood.api.v1.openapi.model.UsuariosModelOpenApi;
import com.algafood.api.v2.model.CidadeModelV2;
import com.algafood.api.v2.model.CozinhaModelV2;
import com.algafood.api.v2.openapi.model.CidadesModelV2OpenApi;
import com.algafood.api.v2.openapi.model.CozinhasModelV2OpenApi;
import com.fasterxml.classmate.TypeResolver;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RepresentationBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Response;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.json.JacksonModuleRegistrar;
import springfox.documentation.spring.web.plugins.Docket;


@Configuration
@EnableOpenApi
//@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SpringFoxConfig implements WebMvcConfigurer{

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("index.html")
		.addResourceLocations("classpath:/META-INF/resources/");
	}

	@Bean
	public JacksonModuleRegistrar springFoxJacksonConfig() {
		return objectMapper -> objectMapper.registerModule(new JavaTimeModule());
	}
	
	@Bean
	public Docket apiDocketV1() {
		// Acessar documentacao API
		// http://localhost:8080/swagger-ui/index.html#/
		
		TypeResolver typeResolver = new TypeResolver();
		
		return new Docket(DocumentationType.OAS_30)
				.groupName("V1")
				.select()
					.apis(RequestHandlerSelectors.basePackage("com.algafood.api"))
					.paths(PathSelectors.ant("/v1/**"))
					.build()
				.useDefaultResponseMessages(false)
				.globalResponses(HttpMethod.GET, globalGetResponseMessages())
				.globalResponses(HttpMethod.POST, globalPostPutResponseMessages())
				.globalResponses(HttpMethod.PUT, globalPostPutResponseMessages())
				.globalResponses(HttpMethod.DELETE, globalDeleteResponseMessages())
				.additionalModels(typeResolver.resolve(Problem.class))
				.ignoredParameterTypes(ServletWebRequest.class,
						URL.class, URI.class, URLStreamHandler.class, Resource.class,
						File.class, InputStream.class)
				.directModelSubstitute(Pageable.class, PageableModelOpenApi.class)
				.directModelSubstitute(Links.class, LinksModelOpenApi.class)
				.alternateTypeRules(AlternateTypeRules.newRule(
						typeResolver.resolve(CollectionModel.class, RestauranteBasicoModel.class), 
						RestaurantesBasicoModelOpenApi.class))
				.alternateTypeRules(AlternateTypeRules.newRule(
						typeResolver.resolve(CollectionModel.class, UsuarioModel.class), 
						UsuariosModelOpenApi.class))
				.alternateTypeRules(AlternateTypeRules.newRule(
						typeResolver.resolve(CollectionModel.class, PermissaoModel.class), 
						PermissoesModelOpenApi.class))
				.alternateTypeRules(AlternateTypeRules.newRule(
						typeResolver.resolve(CollectionModel.class, GrupoModel.class), 
						GruposModelOpenApi.class))
				.alternateTypeRules(AlternateTypeRules.newRule(
						typeResolver.resolve(CollectionModel.class, EstadoModel.class), 
						EstadosModelOpenApi.class))
				.alternateTypeRules(AlternateTypeRules.newRule(
						typeResolver.resolve(CollectionModel.class, FormaPagamentoModel.class), 
						FormasPagamentoModelOpenApi.class))
				.alternateTypeRules(AlternateTypeRules.newRule(
						typeResolver.resolve(CollectionModel.class, ProdutoModel.class), 
						ProdutosModelOpenApi.class))
				.alternateTypeRules(AlternateTypeRules.newRule(
						typeResolver.resolve(PagedModel.class, CozinhaModel.class),
						CozinhasModelOpenApi.class))
				.alternateTypeRules(AlternateTypeRules.newRule(
						typeResolver.resolve(PagedModel.class, PedidoResumoModel.class),
						PedidosResumoModelOpenApi.class))
				.alternateTypeRules(AlternateTypeRules.newRule(
						typeResolver.resolve(CollectionModel.class, CidadeModel.class), 
						CidadesModelOpenApi.class))
				.apiInfo(apiInfoV1())
				.tags(tagsV1()[0], tagsV1());
	}
	
	@Bean
	public Docket apiDocketV2() {
		// Acessar documentacao API
		// http://localhost:8080/swagger-ui/index.html#/
		
		TypeResolver typeResolver = new TypeResolver();
		
		return new Docket(DocumentationType.OAS_30)
				.groupName("V2")
				.select()
					.apis(RequestHandlerSelectors.basePackage("com.algafood.api"))
					.paths(PathSelectors.ant("/v2/**"))
					.build()
				.useDefaultResponseMessages(false)
				.globalResponses(HttpMethod.GET, globalGetResponseMessages())
				.globalResponses(HttpMethod.POST, globalPostPutResponseMessages())
				.globalResponses(HttpMethod.PUT, globalPostPutResponseMessages())
				.globalResponses(HttpMethod.DELETE, globalDeleteResponseMessages())
				.additionalModels(typeResolver.resolve(Problem.class))
				.ignoredParameterTypes(ServletWebRequest.class,
						URL.class, URI.class, URLStreamHandler.class, Resource.class,
						File.class, InputStream.class)
				.directModelSubstitute(Pageable.class, PageableModelOpenApi.class)
				.directModelSubstitute(Links.class, LinksModelOpenApi.class)
				.alternateTypeRules(AlternateTypeRules.newRule(
						typeResolver.resolve(CollectionModel.class, CidadeModelV2.class), 
						CidadesModelV2OpenApi.class))
				.alternateTypeRules(AlternateTypeRules.newRule(
						typeResolver.resolve(PagedModel.class, CozinhaModelV2.class), 
						CozinhasModelV2OpenApi.class))
				.apiInfo(apiInfoV2())
				.tags(tagsV2()[0], tagsV2());
	}
	
	private List<Response> globalGetResponseMessages() {
		  return Arrays.asList(
			      new ResponseBuilder()
			          .code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
			          .description("Erro interno do Servidor")
			          .representation(MediaType.APPLICATION_JSON)
			          .apply(getProblemaModelReference())
			          .build(),
			      new ResponseBuilder()
			          .code(String.valueOf(HttpStatus.NOT_ACCEPTABLE.value()))
			          .description("Recurso não possui representação que pode ser aceita pelo consumidor")
			          .build()
			  );
	}
	
	private List<Response> globalPostPutResponseMessages() {
		  return Arrays.asList(
			      new ResponseBuilder()
			          .code(String.valueOf(HttpStatus.BAD_REQUEST.value()))
			          .description("Requisição inválida (erro do cliente)")
			          .representation(MediaType.APPLICATION_JSON)
			          .apply(getProblemaModelReference())
			          .build(),
			      new ResponseBuilder()
			          .code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
			          .description("Erro interno do Servidor")
			          .representation(MediaType.APPLICATION_JSON)
			          .apply(getProblemaModelReference())
			          .build(),
			      new ResponseBuilder()
			          .code(String.valueOf(HttpStatus.NOT_ACCEPTABLE.value()))
			          .description("Recurso não possui representação que pode ser aceita pelo consumidor")
			          .build(),
			      new ResponseBuilder()
			          .code(String.valueOf(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value()))
			          .description("Requisição recusada porque o corpo está em um formato não suportado")
			          .representation(MediaType.APPLICATION_JSON)
			          .apply(getProblemaModelReference())
			          .build()
			  );
	}
	
	private List<Response> globalDeleteResponseMessages() {
		  return Arrays.asList(
			      new ResponseBuilder()
			          .code(String.valueOf(HttpStatus.BAD_REQUEST.value()))
			          .description("Requisição inválida (erro do cliente)")
			          .representation(MediaType.APPLICATION_JSON)
			          .apply(getProblemaModelReference())
			          .build(),
			      new ResponseBuilder()
			          .code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
			          .description("Erro interno do Servidor")
			          .representation(MediaType.APPLICATION_JSON)
			          .apply(getProblemaModelReference())
			          .build()
			  );
	}
	
	private ApiInfo apiInfoV1() {
		return new ApiInfoBuilder()
				.title("AlgaFood API - (Depreciada) :: Abner J Pelisser")
				.description("Api desenvolvida no curso Especialista Spring Rest - AlgaWorks.<>br>"
						+ "Essa API eást depreciada e deixará de existir a partir de <strong>dd/MM/aaaa</strong>")
				.version("1")
				.contact(new Contact("Abner J Pelisser", "https://github.com/abnerjp", "abner.pelisser@gmail.com"))
				.build();
	}
	
	private ApiInfo apiInfoV2() {
		return new ApiInfoBuilder()
				.title("AlgaFood API :: Abner J Pelisser")
				.description("Api desenvolvida no curso Especialista Spring Rest - AlgaWorks")
				.version("2")
				.contact(new Contact("Abner J Pelisser", "https://github.com/abnerjp", "abner.pelisser@gmail.com"))
				.build();
	}
	
	private Consumer<RepresentationBuilder> getProblemaModelReference() {
			return r -> r.model(m -> m.name("Problema")
					.referenceModel(ref -> ref.key(k -> k.qualifiedModelName(
							q -> q.name("Problema").namespace("com.algafood.api.exceptionhandler")))));
	}
	
	private Tag[] tagsV1() {
		return new Tag[] {
			new Tag("Cidades", "Gerencia as cidades"),
			new Tag("Grupos", "Gerencia os grupos de usuários"),
			new Tag("Cozinhas", "Gerencia as cozinhas"),
			new Tag("Formas Pagamento", "Gerencia as formas de pagamento"),
			new Tag("Pedidos", "Gerencia os pedidos"),
			new Tag("Restaurantes", "Gerencia os restaurantes"),
			new Tag("Estados", "Gerencia os estados"),
			new Tag("Produtos", "Gerencia os produtos de restaurantes"),
			new Tag("Usuários", "Gerencia os usuários"),
			new Tag("Estatísticas", "Estatísticas da AlgaFood"),
			new Tag("Permissões", "Gerencia as permissões")
		};
	}
	
	private Tag[] tagsV2() {
		return new Tag[] {
			new Tag("Cidades", "Gerencia as cidades"),
			new Tag("Cozinhas", "Gerencia as cozinhas")
		};
	}
}
