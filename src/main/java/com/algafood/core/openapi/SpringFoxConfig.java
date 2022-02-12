package com.algafood.core.openapi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
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
				.apiInfo(apiInfo())
				.tags(tags()[0], tags());
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
