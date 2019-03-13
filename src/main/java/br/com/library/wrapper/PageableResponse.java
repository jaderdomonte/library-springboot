package br.com.library.wrapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PageableResponse<T> extends PageImpl<T> {

	private static final long serialVersionUID = 1L;
	
	private boolean last;
	private boolean first;
	private int totalPages;

	public PageableResponse() {
		super(new ArrayList<>());
	}

	public PageableResponse(@JsonProperty("content") List<T> content, 
							@JsonProperty("number") int page,
							@JsonProperty("size") int size,
							@JsonProperty("sort") Sort sort,
							@JsonProperty("totalElements") long totalElements) {
		super(content, new PageRequest(page, size, sort), totalElements);
	}

	public boolean isLast() {
		return last;
	}

	public void setLast(boolean last) {
		this.last = last;
	}

	public boolean isFirst() {
		return first;
	}

	public void setFirst(boolean first) {
		this.first = first;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
}
