package com.msa.product.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;

@Configuration
public class WebConfig implements WebFluxConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		// TODO Auto-generated method stub
		//WebMvcConfigurer.super.addCorsMappings(registry);
		registry.addMapping("/**").allowedOrigins("http://localhost:8080")
			.allowedMethods(
		    	HttpMethod.GET.name(),
		    	HttpMethod.HEAD.name(),
		    	HttpMethod.POST.name(),
		    	HttpMethod.PUT.name(),
		    	HttpMethod.DELETE.name());
	}
	
}
