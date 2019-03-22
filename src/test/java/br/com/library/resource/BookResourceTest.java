package br.com.library.resource;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import br.com.library.model.Book;
import br.com.library.repository.BookRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class BookResourceTest {

	@Autowired
	private TestRestTemplate restTemplate;
	
	@LocalServerPort
	private int port;
	
	@MockBean
	private BookRepository repository;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void listBookShouldReturnStatusCode401WhenUsernameOrPasswordAreIncorrect() {
		restTemplate = restTemplate.withBasicAuth("1", "1");
		ResponseEntity<String> response = restTemplate.getForEntity("/v1/protected/book/", String.class);
		
		assertThat(response.getStatusCodeValue()).isEqualTo(401);
	}
	
	@Test
	public void getBookByIdShouldReturnStatusCode401WhenUsernameOrPasswordAreIncorrect() {
		restTemplate = restTemplate.withBasicAuth("1", "1");
		ResponseEntity<String> response = restTemplate.getForEntity("/v1/protected/book/{id}", String.class, 1);
		
		assertThat(response.getStatusCodeValue()).isEqualTo(401);
	}
	
	@Test
	public void listBookShouldReturnStatusCode200WhenUsernameOrPasswordAreCorrect() {
		List<Book> bookList = Arrays.asList(new Book(1L), new Book(2L));
		BDDMockito.when(repository.findAll()).thenReturn(bookList);
		
		ResponseEntity<String> response = restTemplate.getForEntity("/v1/protected/book/", String.class);
		
		assertThat(response.getStatusCodeValue()).isEqualTo(200);
	}
	
	@Test
	public void getBookByIdShouldReturnStatusCode200WhenUsernameOrPasswordAreCorrect() {
		Book book = new Book(1L);
		BDDMockito.when(repository.findOne(book.getId())).thenReturn(book);
		
		ResponseEntity<String> response = restTemplate.getForEntity("/v1/protected/book/{id}", String.class, book.getId());
		
		assertThat(response.getStatusCodeValue()).isEqualTo(200);
	}
	
	@Test
	public void getBookByIdShouldReturnStatusCode404WhenUsernameOrPasswordAreCorrectAndBookDoesNotExists() {
		ResponseEntity<String> response = restTemplate.getForEntity("/v1/protected/book/{id}", String.class, -1);
		
		assertThat(response.getStatusCodeValue()).isEqualTo(404);
	}
}
