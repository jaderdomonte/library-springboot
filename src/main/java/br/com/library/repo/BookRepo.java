package br.com.library.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.library.model.Book;

@Repository
public interface BookRepo extends CrudRepository<Book, Long> {

}
