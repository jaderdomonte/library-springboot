package br.com.library.rest.client;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import lombok.Data;

@Data
public class RestClient {
	
	private static final String PASSWORD = "senha";
	private static final String USERNAME = "jader";
	private static final String URL_BASE = "http://localhost:8080/library";
	private RestTemplate restTemplate;
	private String resource;
	private String uri;
	private String username;
	private String password;
	private String pathParam;
	
	private RestClient() {}
	
	private RestClient(String username, String password) {
		this.username = username;
		this.password = password;
		createRestTemplate();
	}

	public static RestClient newInstance() {
		return new RestClient(USERNAME, PASSWORD);
	}
	
	private void createRestTemplate() {
		this.restTemplate = new RestTemplateBuilder()
									.rootUri(URL_BASE+this.resource)
									.basicAuthorization(this.username, this.password)
									.build();
	}
	
	public static RestClient newInstance(String username, String password) {
		return new RestClient(username, password);
	}
	
	public RestClient resource(String resource) {
		this.resource = resource;
		return this;
	}
	
	public RestClient uri(String uri) {
		this.uri = uri;
		return this;
	}
	
	public RestClient pathParam(String pathParam) {
		this.pathParam = pathParam;
		return this;
	}
	
	public <T> ResponseEntity<T> get(Class<T> clazz){
		return this.restTemplate.getForEntity(this.uri, clazz, this.pathParam);
	}
	
	public RestTemplate build() {
		return this.restTemplate;
	}
}
