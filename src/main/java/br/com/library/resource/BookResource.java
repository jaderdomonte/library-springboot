package br.com.library.resource;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.library.exceptions.DataBaseAcessException;
import br.com.library.exceptions.ResourceNotFoundDetails;
import br.com.library.exceptions.ResourceNotFoundException;
import br.com.library.model.Book;
import br.com.library.service.BookService;

@RestController
@RequestMapping(value="/book")
public class BookResource {
	
	private static final String BOOK_NOT_FOUND = "Book not found for ID: ";
	
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
    		throw new ResourceNotFoundException(BOOK_NOT_FOUND+id);
    	}
    	
    	return new ResponseEntity<>(book, HttpStatus.OK);
    }

	private boolean isBookNotExists(Book book) {
		return book == null;
	}
    
    @PostMapping
    @Transactional
    public ResponseEntity<?> save(@RequestBody Book book){
    	return new ResponseEntity<>(service.save(book), HttpStatus.CREATED);
    }
    
    @PutMapping
    @Transactional
    public ResponseEntity<?> update(@RequestBody Book book){
    	service.save(book);
    	
    	if(true) {
    		throw new DataBaseAcessException("Error in Acess DataBase.");
    	}
    	
    	return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @ExceptionHandler(DataBaseAcessException.class)
    public ResponseEntity<?> errorDataBaseAccess(DataBaseAcessException dbaException) {
    	ResourceNotFoundDetails rnfDetails = ResourceNotFoundDetails.builder()
			   	.timestamp(new Date().getTime())
				.status(HttpStatus.CONFLICT.value())
				.title("DataBase Access Error")
				.detail(dbaException.getMessage())
				.message(dbaException.getClass().getName())
				.build();
		return new ResponseEntity<>(rnfDetails, HttpStatus.CONFLICT);
    }
    
    @DeleteMapping(value="/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
    	service.delete(id);
    	return new ResponseEntity<>(HttpStatus.OK);
    }
}
