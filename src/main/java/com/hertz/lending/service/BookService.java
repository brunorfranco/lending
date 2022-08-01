package com.hertz.lending.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hertz.lending.model.Book;
import com.hertz.lending.repository.BookRepository;

@Service
public class BookService {

	private BookRepository bookRepo;

	@Autowired
	public BookService(BookRepository bookRepo) {
		super();
		this.bookRepo = bookRepo;
	}

	public Book add(Book book) {
		return bookRepo.save(book);
	}
	
	public void remove(Optional<Book> optional) {
		if(optional.isPresent()) {
			bookRepo.delete(optional.get());
		}
	}
	
	public Optional<Book> findById(Long id) {
		return bookRepo.findById(id);
	}
	
	public Iterable<Book> findAll() {
		return bookRepo.findAll();
	}
	
}
