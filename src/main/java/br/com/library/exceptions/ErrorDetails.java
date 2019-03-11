package br.com.library.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDetails {

	protected String title;
	protected Integer status;
	protected String detail;
	protected Long timestamp;
	protected String message;
}
