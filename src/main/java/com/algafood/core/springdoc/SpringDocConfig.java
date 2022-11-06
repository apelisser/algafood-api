package com.algafood.core.springdoc;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.OAuthFlow;
import io.swagger.v3.oas.annotations.security.OAuthFlows;
import io.swagger.v3.oas.annotations.security.OAuthScope;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.tags.Tag;

@Configuration
@SecurityScheme(name = "security_auth", 
	type = SecuritySchemeType.OAUTH2, 
	flows = @OAuthFlows(authorizationCode = @OAuthFlow(
			authorizationUrl = "${springdoc.oAuthFlow.authorizationUrl}",
			tokenUrl = "${springdoc.oAuthFlow.tokenUrl}",
			scopes = {
					@OAuthScope(name = "READ", description = "read scope"),
					@OAuthScope(name = "WRITE", description = "write scope")
			}
	)))
public class SpringDocConfig {

	@Bean
	public OpenAPI openAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("AlgaFood API")
						.version("v1")
						.description("REST API do AlgaFood")
						.license(new License()
								.name("Apache 2.0")
								.url("http://springdoc.com")
								)
						
						).externalDocs(new ExternalDocumentation()
								.description("Abner Jácomo Pelisser")
								.url("https://www.linkedin.com/in/abner-pelisser/")
						).tags(loadTags());
	}
	
	private List<Tag> loadTags() {
		return Arrays.asList(
				new Tag().name("Cidades").description("Gerencia as cidades")
			);
	}
	
}
