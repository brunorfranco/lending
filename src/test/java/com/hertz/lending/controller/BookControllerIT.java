package com.hertz.lending.controller;

import java.util.ArrayList;
import java.util.List;

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
	public void recordNewBook_sameBookTwice_fail() {
		Book janeEyre = new Book("Jane Eyre", "Charlotte Bronte", categoryService.findByName("Fantasy"));
		controller.addNewBook(janeEyre);
		
		Book repeatBook = new Book("Jane Eyre", "Charlotte Bronte", categoryService.findByName("Fantasy"));
		controller.addNewBook(repeatBook);
		Iterable<Book> retrieveBooks = controller.retrieveBooks();
		
		List<Book> resultList = new ArrayList<>();
		retrieveBooks.forEach(resultList::add);
		
		long count = resultList.stream()
		        .filter(p -> p.getTitle().equals("Jane Eyre"))
		        .count();
		Assert.assertEquals(count, 1l);
		
	}
	
	@Test
	public void deleteBook_validBook_true() {
		Iterable<Book> priorRetrievedBooks = controller.retrieveBooks();
		controller.deleteBook("Harry Potter and the Philosophical Stone", "J. K. Rolling");
		
		Iterable<Book> afterRetrievedBooks = controller.retrieveBooks();
		Assert.assertTrue(!priorRetrievedBooks.equals(afterRetrievedBooks));
	}

}
