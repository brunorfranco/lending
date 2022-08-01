package com.hertz.lending.repository;

import org.springframework.data.repository.CrudRepository;

import com.hertz.lending.model.Book;
import com.hertz.lending.model.BookId;

public interface BookRepository extends CrudRepository<Book, BookId>{
	
	Book findByTitle(String title);
}
