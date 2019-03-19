package br.com.library.model;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity(name="author")
@EqualsAndHashCode(callSuper=false)
public class Author extends AbstractEntity {

	private static final long serialVersionUID = -5416252239364162615L;
	
	public Author() {}
	
	public Author(Long id, String name) {
		super(id);
		this.name = name;
	}
	
	private String name;

	@Override
	public String toString() {
		return "Author [name=" + name + "]";
	}
}
