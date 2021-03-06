package br.com.library.rest.client;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import br.com.library.handler.RestResponseExceptionHandler;
import br.com.library.model.Author;
import br.com.library.model.Book;

public class RestTemplateClient {
	
	private static final String URL = "http://localhost:8080/library/v1/protected/book";
	private static final String URL_ADMIN = "http://localhost:8080/library/v1/admin/book";
	private static RestTemplate restTemplateProtected;
	private static RestTemplate restTemplateAdmin;
	
	public static void main(String[] args) {
		restTemplateProtected = new RestTemplateBuilder()
										.rootUri(URL)
										.basicAuthorization("jader", "senha")
										.errorHandler(new RestResponseExceptionHandler())
										.build();
		restTemplateAdmin = new RestTemplateBuilder()
										.rootUri(URL_ADMIN)
										.errorHandler(new RestResponseExceptionHandler())
										.basicAuthorization("jader", "senha")
										.build();
//		getForObject();
//		getForEntity();
//		getExchange();
//		getAllForObject();
//		getAllForEntity();
//		getAllExchange();
//		postForObject();
//		postForEntity();
//		postForExchange();
//		put(createBookWithId());
		delete(20L);
	}

	private static void getAllExchange() {
		ResponseEntity<List<Book>> bookList = restTemplateProtected.exchange("/", HttpMethod.GET, null, new ParameterizedTypeReference<List<Book>>() {});
		System.out.println(bookList.getBody());
	}

	private static void getAllForObject() {
		Book[] books = restTemplateProtected.getForObject("/", Book[].class);
		System.out.println(Arrays.toString(books));
	}

	private static void getForEntity() {
		ResponseEntity<Book> forEntity = restTemplateProtected.getForEntity("/{id}", Book.class, 1);
		System.out.println(forEntity);
		System.out.println(forEntity.getBody());
	}

	private static void getForObject() {
		Book book = restTemplateProtected.getForObject("/{id}", Book.class, 1);
		System.out.println(book);
	}
	
	private static void getExchange() {
		ResponseEntity<List<Book>> bookList = restTemplateProtected.exchange("/", HttpMethod.GET, null, new ParameterizedTypeReference<List<Book>>() {});
		System.out.println(bookList);
	}
	
	private static void getAllForEntity() {
		ResponseEntity<Book[]> forEntity = restTemplateProtected.getForEntity("/", Book[].class);
		System.out.println(Arrays.asList(forEntity));
	}
	
	private static Book postForObject() {
		Book book = restTemplateAdmin.postForObject("/", createBook(), Book.class);
		System.out.println(book);
		return book;
	}
	
	private static ResponseEntity<Book> postForEntity() {
		ResponseEntity<Book> book = restTemplateAdmin.postForEntity("/", createBook(), Book.class);
		System.out.println(book);
		return book;
	}
	
	private static ResponseEntity<Book> postForExchange(){
		ResponseEntity<Book> exchange = restTemplateAdmin.exchange("/", HttpMethod.POST, new HttpEntity<>(createBook(), createHeaders()), Book.class);
		System.out.println(exchange);
		return exchange;
	}
	
	private static Book createBook() {
		Book book = Book.builder()
						 .title("Livro 1")
						 .author(Author.builder()
								   	   .id(1L)
								   	   .name("Author 1")
								   	   .build())
						 .build();
		return book;
	}
	
	private static Book createBookWithId() {
		Book book = createBook();
		book.setId(1L);
		
		return book;
	}
	
	private static HttpHeaders createHeaders() {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		
		return httpHeaders;
	}
	
	private static void put(Book book) {
		restTemplateAdmin.put("/", book);
	}
	
	private static void delete(Long id) {
		restTemplateAdmin.delete("/{id}", id);
	}
}
