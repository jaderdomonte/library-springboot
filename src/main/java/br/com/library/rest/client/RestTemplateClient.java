package br.com.library.rest.client;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import br.com.library.model.Book;
import br.com.library.wrapper.PageableResponse;

public class RestTemplateClient {
	
	private static final String URL = "http://localhost:8080/library/v1/protected/book";
	
	public static void main(String[] args) {
		RestTemplate restTemplate = new RestTemplateBuilder()
										.rootUri(URL)
										.basicAuthorization("jader", "senha")
										.build();
//		Book book = restTemplate.getForObject("/{id}", Book.class, 1);
//		System.out.println(book);
//		
//		ResponseEntity<Book> forEntity = restTemplate.getForEntity("/{id}", Book.class, 1);
//		System.out.println(forEntity);
//		System.out.println(forEntity.getBody());
//		
//		Book[] books = restTemplate.getForObject("/", Book[].class);
//		System.out.println(Arrays.toString(books));
//		
//		ResponseEntity<List<Book>> bookList = restTemplate.exchange("/", HttpMethod.GET, null, new ParameterizedTypeReference<List<Book>>() {});
//		System.out.println(bookList.getBody());
		
		ResponseEntity<PageableResponse<Book>> exchange = restTemplate.exchange("/", HttpMethod.GET, null, new ParameterizedTypeReference<PageableResponse<Book>>() {});
		System.out.println(exchange.getBody());
	}
}
