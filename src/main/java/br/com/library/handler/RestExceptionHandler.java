package br.com.library.handler;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

import br.com.library.exceptions.ErrorDetails;
import br.com.library.exceptions.ResourceNotFoundDetails;
import br.com.library.exceptions.ResourceNotFoundException;
import br.com.library.exceptions.ValidationErrorDetail;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handlerResourceNotFoundException(ResourceNotFoundException rnfException){
		ResourceNotFoundDetails rnfDetails = buildResourceNotFoundDetails(rnfException);

		return new ResponseEntity<>(rnfDetails, HttpStatus.NOT_FOUND);
	}

	private ResourceNotFoundDetails buildResourceNotFoundDetails(ResourceNotFoundException rnfException) {
		ResourceNotFoundDetails rnfDetails = ResourceNotFoundDetails.builder().build();
		rnfDetails.setTimestamp(new Date().getTime());
		rnfDetails.setStatus(HttpStatus.NOT_FOUND.value());
		rnfDetails.setTitle("Resource Not Found");
		rnfDetails.setDetail(rnfException.getMessage());
		rnfDetails.setMessage(rnfException.getClass().getName());
		return rnfDetails;
	}
	
	@Override
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException manvException, 
															   HttpHeaders headers, 
															   HttpStatus status, 
															   WebRequest request) {

		List<FieldError> fieldErrors = manvException.getBindingResult().getFieldErrors();
		
		ValidationErrorDetail vedDetails = buildValidationErrorDetail(manvException, fieldErrors);

		return new ResponseEntity<>(vedDetails, HttpStatus.BAD_REQUEST);
	}
	
	private ValidationErrorDetail buildValidationErrorDetail(MethodArgumentNotValidException manvException,
			List<FieldError> fieldErrors) {
		ValidationErrorDetail vedDetails = ValidationErrorDetail.builder().build();
		vedDetails.setTimestamp(new Date().getTime());
		vedDetails.setStatus(HttpStatus.BAD_REQUEST.value());
		vedDetails.setTitle("Argument Not Valid");
		vedDetails.setDetail("Argument Not Valid");
		vedDetails.setMessage(manvException.getClass().getName());
		vedDetails.setField(getFieldsError(fieldErrors));
		vedDetails.setFieldMessage(getFieldsMessageError(fieldErrors));
		return vedDetails;
	}

	private String getFieldsMessageError(List<FieldError> fieldErrors) {
		String fieldsMessage = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(","));
		return fieldsMessage;
	}

	private String getFieldsError(List<FieldError> fieldErrors) {
		String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(","));
		return fields;
	}
	
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, 
														  Object body, 
														  HttpHeaders headers, 
														  HttpStatus status, 
														  WebRequest request) {

		ErrorDetails errorDetails = buildErrorDetails(ex, status);
		
		return new ResponseEntity<>(errorDetails, headers, status);
	}

	private ErrorDetails buildErrorDetails(Exception ex, HttpStatus status) {
		ErrorDetails errorDetails = new ErrorDetails();
		errorDetails.setTimestamp(new Date().getTime());
		errorDetails.setStatus(status.value());
		errorDetails.setTitle("Internal Exception");
		errorDetails.setDetail(ex.getMessage());
		errorDetails.setMessage(ex.getClass().getName());
		
		return errorDetails;
	}
}
