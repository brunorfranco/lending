package com.hertz.lending.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hertz.lending.model.Book;
import com.hertz.lending.model.BookId;
import com.hertz.lending.repository.BookRepository;

@Service
public class BookService {

	private BookRepository bookRepo;

	@Autowired
	public BookService(BookRepository bookRepo) {
		super();
		this.bookRepo = bookRepo;
	}

	@Transactional
	public Book add(Book book) {
		return bookRepo.save(book);
	}
	
	@Transactional
	public void remove(Optional<Book> optional) {
		if(optional.isPresent()) {
			bookRepo.delete(optional.get());
		}
	}
	
	public Optional<Book> findById(String title, String author) {
		BookId id = new BookId(title, author);
		return bookRepo.findById(id);
	}
	
	public Iterable<Book> findAll() {
		return bookRepo.findAll();
	}
	
}
