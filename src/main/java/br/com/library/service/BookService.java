package br.com.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.library.model.Book;
import br.com.library.repo.BookRepository;

@Service
public class BookService {

	private BookRepository dao;
	
	@Autowired
	public BookService(BookRepository dao) {
		this.dao = dao;
	}
	
	public Page<Book> listAll(Pageable pageable){
		return dao.findAll(pageable);
	}
	
	public Book findById(Long id) {
		return dao.findOne(id);
	}
	
	public Book save(Book book) {
		return dao.save(book);
	}
	
	public void delete(Long id) {
		dao.delete(id);
	}

	public BookRepository getDao() {
		return dao;
	}

	public void setDao(BookRepository dao) {
		this.dao = dao;
	}
}
