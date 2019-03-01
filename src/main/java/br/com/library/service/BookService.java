package br.com.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.library.model.Book;
import br.com.library.repo.BookRepo;

@Service
public class BookService {

	private BookRepo dao;
	
	@Autowired
	public BookService(BookRepo dao) {
		this.dao = dao;
	}
	
	public List<Book> listAll(){
		return (List<Book>) dao.findAll();
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

	public BookRepo getDao() {
		return dao;
	}

	public void setDao(BookRepo dao) {
		this.dao = dao;
	}
}
