package com.hertz.lending.repository;

import org.springframework.data.repository.CrudRepository;

import com.hertz.lending.model.Book;

public interface BookRepository extends CrudRepository<Book, Long>{
	
	Book findByTitle(String title);
}
