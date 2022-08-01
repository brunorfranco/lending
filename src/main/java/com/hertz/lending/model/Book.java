package com.hertz.lending.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
@IdClass(BookId.class)
public class Book {

	@Id
	private String title;
	@Id
	private String author;

	@ManyToOne(optional = false)
	private Category category;

	public Book(String title, String author, Category category) {
		super();
		this.title = title;
		this.author = author;
		this.category = category;
	}

	public Book() {
		super();
	}

}
