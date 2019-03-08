package br.com.library.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping(value="/book")
public class BookResource {
	
	private static final String BOOK_NOT_FOUND = "Book not found!";
	private BookService service;
	
	@Autowired
	public BookResource(BookService service){
		this.service = service;
	}

    @GetMapping(value="/")
    public ResponseEntity<?> listAll(){
        return new ResponseEntity<>(service.listAll(), HttpStatus.OK);
    }
    
    @GetMapping(value="/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id){
    	Book book = service.findById(id);
    	
    	if(isBookNotExists(book)) {
    		throw new ResourceNotFoundException(BOOK_NOT_FOUND);
    	}
    	
    	return new ResponseEntity<>(book, HttpStatus.OK);
    }

	private boolean isBookNotExists(Book book) {
		return book == null;
	}
    
    @PostMapping
    public ResponseEntity<?> save(@RequestBody Book book){
    	return new ResponseEntity<>(service.save(book), HttpStatus.CREATED);
    }
    
    @PutMapping
    public ResponseEntity<?> update(@RequestBody Book book){
    	service.save(book);
    	return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @DeleteMapping(value="/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
    	service.delete(id);
    	return new ResponseEntity<>(HttpStatus.OK);
    }
}
