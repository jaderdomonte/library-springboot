package br.com.library.repo;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.library.model.Book;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookRepositoryTest {
	
	@Autowired
	private BookRepository repo;

	@Test
	public void deveRetornarLivroPorId() {
		Book book = repo.findOne(1L);
		
		assertThat(book.getTitle(), equalTo("Crianças Dinamarquesas"));
	}
	
	@Test
	public void naoDeveRetornarLivroPorIdInexistente() {
		Book book = repo.findOne(4L);
		
		assertThat(book, nullValue());
	}
}
