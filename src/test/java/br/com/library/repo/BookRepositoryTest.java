package br.com.library.repo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.library.model.Book;
import br.com.library.repository.BookRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookRepositoryTest {
	
	@Autowired
	private BookRepository repo;

	@Test
	public void deveRetornarLivroPorId() {
		Book book = repo.findOne(1L);
		
		assertThat(book.getTitle()).isEqualTo("Crianças Dinamarquesas");
	}
	
	@Test
	public void naoDeveRetornarLivroPorIdInexistente() {
		Book book = repo.findOne(4L);
		
		assertThat(book).isNull();
	}
}
