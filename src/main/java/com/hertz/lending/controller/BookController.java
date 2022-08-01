package com.hertz.lending.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hertz.lending.model.Book;
import com.hertz.lending.service.BookService;

@RestController
public class BookController {

	private BookService bookService;
	
	@Autowired
	public BookController(BookService bookService) {
		super();
		this.bookService = bookService;
	}

	@PostMapping("/books")
	Book addNewBook(@RequestBody Book book) {
		return bookService.add(book);
	}

	@DeleteMapping("/books/{id}")
	public void deleteBook(@PathVariable Long id) {
		bookService.remove(bookService.findById(id));
	}
	
	@GetMapping("/books")
	public Iterable<Book> retrieveBooks() {
		return bookService.findAll();
	}
	
}
