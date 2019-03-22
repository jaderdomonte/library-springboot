package br.com.library.model;


import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name="book")
@EqualsAndHashCode(callSuper=false)
public class Book extends AbstractEntity {

	private static final long serialVersionUID = 6515550411704844803L;
	
	public Book(Long id) {
		super(id);
	}

	@NotEmpty(message = "O campo title é obrigatório")
    private String title;
    
    @ManyToOne
    @JoinColumn(name="id_author")
    private Author author;
    
	@Override
	public String toString() {
		return "Book [title=" + title + ", author=" + author + "]";
	}
}
