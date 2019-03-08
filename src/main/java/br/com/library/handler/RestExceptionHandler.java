package br.com.library.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.library.exceptions.ResourceNotFoundDetails;
import br.com.library.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handlerResourceNotFoundException(ResourceNotFoundException rnfException){
		ResourceNotFoundDetails rnfDetails = ResourceNotFoundDetails.builder()
																   	.timestamp(new Date().getTime())
																	.status(HttpStatus.NOT_FOUND.value())
																	.title("Resource Not Found")
																	.detail(rnfException.getMessage())
																	.message(rnfException.getClass().getName())
																	.build();
		return new ResponseEntity<>(rnfDetails, HttpStatus.NOT_FOUND);
	}
}
