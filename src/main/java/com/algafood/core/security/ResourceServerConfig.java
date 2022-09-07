package com.algafood.core.security;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

@Configuration
@EnableWebSecurity
public class ResourceServerConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.anyRequest().authenticated()
			.and()
				.cors()
			.and()
				.oauth2ResourceServer().jwt();
	}
	
	@Bean
	public JwtDecoder jwtDecoder() {
		var secretKey = new SecretKeySpec("asdfsadf123sdfasd564fsd987wet987gf546gkjmh64ert87sad123fg654dgh".getBytes(), "HmacSHA256");
		return NimbusJwtDecoder.withSecretKey(secretKey).build();
	}
	
}
