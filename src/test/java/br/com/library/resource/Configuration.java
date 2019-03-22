package br.com.library.resource;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class Configuration {
	
	@Bean
	public RestTemplateBuilder restTemplateBuilder() {
		return new RestTemplateBuilder().basicAuthorization("jader", "senha");
	}
}
