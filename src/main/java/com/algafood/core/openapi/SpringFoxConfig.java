package com.algafood.core.openapi;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Response;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
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
	public Docket apiDocket() {
		// Acessar documentacao API
		// http://localhost:8080/swagger-ui/index.html#/
		
		return new Docket(DocumentationType.OAS_30)
				.select()
					.apis(RequestHandlerSelectors.basePackage("com.algafood.api"))
					.paths(PathSelectors.any())
					.build()
				.useDefaultResponseMessages(false)
				.globalResponses(HttpMethod.GET, globalGetResponseMessages())
				.apiInfo(apiInfo())
				.tags(tags()[0], tags());
	}
	
	private List<Response> globalGetResponseMessages() {
		  return Arrays.asList(
			      new ResponseBuilder()
			          .code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
			          .description("Erro interno do Servidor")
			          .build(),
			      new ResponseBuilder()
			          .code(String.valueOf(HttpStatus.NOT_ACCEPTABLE.value()))
			          .description("Recurso não possui representação que pode ser aceita pelo consumidor")
			          .build()
			  );
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("AlgaFood API :: Abner J Pelisser")
				.description("Api desenvolvida no curso Especialista Spring Rest - AlgaWorks")
				.version("1")
				.contact(new Contact("Abner J Pelisser", "https://github.com/abnerjp", "abner.pelisser@gmail.com"))
				.build();
	}
	
	private Tag[] tags() {
		return new Tag[] {
			new Tag("Cidades", "Gerencia cidades")	
		};
	}
}
