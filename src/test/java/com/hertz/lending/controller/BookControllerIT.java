package com.hertz.lending.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hertz.lending.model.Book;
import com.hertz.lending.service.CategoryService;

@SpringBootTest
public class BookControllerIT {

	private BookController controller;
	
	private CategoryService categoryService;

	@Autowired
	public BookControllerIT(BookController controller, CategoryService categoryService) {
		super();
		this.controller = controller;
		this.categoryService = categoryService;
	}

	@Before
	public void startup() {
	}

	@Test
	public void recordNewBook_validBook_true() {
		Book book = new Book("The Hobbit", "J. K. K. Tolkien", categoryService.findByName("Fantasy"));
		Book addedBook = controller.addNewBook(book);
		Assert.assertNotNull(addedBook);
		
		Iterable<Book> retrieveBooks = controller.retrieveBooks();
		System.out.println(retrieveBooks);
	}
	
	@Test
	public void deleteBook_validBook_true() {
		controller.deleteBook(1l);
		
		Iterable<Book> retrieveBooks = controller.retrieveBooks();
		System.out.println(retrieveBooks);
	}

}
