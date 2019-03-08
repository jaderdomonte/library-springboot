package br.com.library.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.CONFLICT)
public class DataBaseAcessException extends RuntimeException {
	
	private static final long serialVersionUID = 7117581385350242900L;

	public DataBaseAcessException(String message) {
		super(message);
	}
}
