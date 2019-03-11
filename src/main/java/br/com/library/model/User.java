package br.com.library.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User extends AbstractEntity {
	
	private static final long serialVersionUID = 8320572619638126684L;

	@NotEmpty
	@Column(unique = true)
	private String username;
	
	@NotEmpty
	@JsonIgnore
	private String password;
	
	@NotEmpty
	private String name;
	
	@NotEmpty
	private Boolean admin;
}
