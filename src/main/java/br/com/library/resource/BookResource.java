package br.com.library.resource;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.library.exceptions.ResourceNotFoundException;
import br.com.library.model.Book;
import br.com.library.service.BookService;

@RestController
@RequestMapping(path = "v1")
public class BookResource {

	private static final String BOOK_NOT_FOUND = "Book not found for ID: ";

	private BookService service;

	@Autowired
	public BookResource(BookService service) {
		this.service = service;
	}

	@GetMapping(path = "protected/book")
	public ResponseEntity<?> listAll(Pageable pageable) {
		return new ResponseEntity<>(service.listAll(pageable), HttpStatus.OK);
	}

	@GetMapping(path = "protected/book/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Long id) {
		Book book = service.findById(id);

		if (isBookNotExists(book)) {
			throw new ResourceNotFoundException(BOOK_NOT_FOUND + id);
		}

		return new ResponseEntity<>(book, HttpStatus.OK);
	}

	private boolean isBookNotExists(Book book) {
		return book == null;
	}

	@Transactional
	@PostMapping(path = "admin/book")
	public ResponseEntity<?> save(@Valid @RequestBody Book book) {
		return new ResponseEntity<>(service.save(book), HttpStatus.CREATED);
	}

	@Transactional
	@PutMapping(path = "admin/book")
	public ResponseEntity<?> update(@RequestBody Book book) {
		service.save(book);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Transactional
	@DeleteMapping(path = "admin/book/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		service.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
