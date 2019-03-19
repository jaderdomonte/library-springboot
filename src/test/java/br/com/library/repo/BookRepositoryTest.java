package br.com.library.repo;

import static org.assertj.core.api.Assertions.*;

import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.rules.ExpectedException;
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
	private BookRepository repository;

	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void shouldPersistBook() {
		Book book = saveNewBook();
		
		assertThat(book.getId()).isNotNull();
		assertThat(book.getTitle()).isEqualTo("Book 1");
	}

	private Book saveNewBook() {
		Book book = createNewBook();
		
		this.repository.save(book);
		return book;
	}
	
	@Test
	public void shouldDeleteBook() {
		Book book = saveNewBook();
		this.repository.delete(book);
		
		assertThat(this.repository.findOne(book.getId())).isNull();
	}
	
	@Test
	public void shouldUpdateBook() {
		Book book = saveNewBook();
		book.setTitle("Book 2");
		
		this.repository.save(book);
		book = this.repository.findOne(book.getId());
		
		assertThat(book.getTitle()).isEqualTo("Book 2");
	}

	private Book createNewBook() {
		Book book = new Book();
		book.setTitle("Book 1");
		return book;
	}
	
	@Test
	public void shouldNotPersistBookWithTitleIsNull() {
		thrown.expect(ConstraintViolationException.class);
//		thrown.expectMessage("O campo title é obrigatório");
		this.repository.save(new Book());
	}
}
