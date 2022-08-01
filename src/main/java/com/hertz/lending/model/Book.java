package com.hertz.lending.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToMany;

import lombok.Data;

@Entity
@Data
@IdClass(BookId.class)
public class Book {

	@Id
	private String title;
	@Id
	private String author;

	/**
	 * Requirement: each book can have multiple categories
	 */
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Category> categories;

	public Book(String title, String author, List<Category> categories) {
		super();
		this.title = title;
		this.author = author;
		this.categories = categories;
	}

	public Book() {
		super();
	}

}
