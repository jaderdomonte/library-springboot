package br.com.library.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ResponseStatus(value=HttpStatus.BAD_REQUEST)
public class ValidationErrorDetail extends ErrorDetails {

	private String field;
	private String fieldMessage;
	
}
