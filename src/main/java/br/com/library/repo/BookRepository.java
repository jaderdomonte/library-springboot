package br.com.library.repo;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.library.model.Book;

@Repository
public interface BookRepository extends PagingAndSortingRepository<Book, Long> {

}
