package com.algafood.core.security;
// Fonte: https://spring.io/blog/2015/06/08/cors-support-in-spring-framework#filter-based-cors-support

import java.util.Arrays;
import java.util.Collections;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilterRegistrationBean() {
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOriginPattern("*");
		config.setAllowedOrigins(Arrays.asList("http://127.0.0.1:8080", "http://127.0.0.1:8001"));
		config.setAllowedMethods(Collections.singletonList("*"));
		config.setAllowedHeaders(Collections.singletonList("*"));
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);

		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>();
		bean.setFilter(new CorsFilter(source));
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		
		return bean;
	}

}