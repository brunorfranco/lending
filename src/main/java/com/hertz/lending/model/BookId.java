package com.hertz.lending.model;

import java.io.Serializable;

import lombok.Data;

/**
 * As per specified requirements, there should only be one copy of each book
 * Therefore a composite key with title & author was created so this will
 * be guaranteed at the DB constraint level
 * 
 * @author Bruno Franco
 *
 */
@Data
public class BookId implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String title;
	private String author;

    public BookId(String title, String author) {
        this.title = title;
        this.author = author;
    }

	public BookId() {
		super();
	}

}