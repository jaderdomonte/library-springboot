package br.com.library.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResourceNotFoundDetails {
	
	private String title;
	private Integer status;
	private String detail;
	private Long timestamp;
	private String message;
}
