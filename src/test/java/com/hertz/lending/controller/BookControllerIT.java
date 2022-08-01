package com.hertz.lending.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hertz.lending.model.Book;
import com.hertz.lending.service.CategoryService;
import com.hertz.lending.util.AppConstants;

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
	public void recordNewBook_validBookSingleCategory_true() {
		Book book = new Book(AppConstants.THE_HOBBIT_TITLE, AppConstants.J_R_R_TOLKIEN, Arrays.asList(categoryService.findByName(AppConstants.FANTASY_CATEGORY)));
		Book addedBook = controller.addNewBook(book);
		Assert.assertNotNull(addedBook);
	}
	
	@Test
	public void recordNewBook_validBookMultipleCategories_true() {
		Book book = new Book(AppConstants.THE_HOBBIT_TITLE, AppConstants.J_R_R_TOLKIEN, Arrays.asList(categoryService.findByName(AppConstants.FANTASY_CATEGORY), categoryService.findByName(AppConstants.ROMANCE_CATEGORY)));
		Book addedBook = controller.addNewBook(book);
		Assert.assertNotNull(addedBook);
	}
	
	@Test
	public void recordNewBook_sameBookTwice_fail() {
		Book janeEyre = new Book(AppConstants.JANE_EYRE_TITLE, AppConstants.CHARLOTTE_BRONTE, Arrays.asList(categoryService.findByName(AppConstants.FANTASY_CATEGORY)));
		controller.addNewBook(janeEyre);
		
		Book repeatBook = new Book(AppConstants.JANE_EYRE_TITLE, AppConstants.CHARLOTTE_BRONTE, Arrays.asList(categoryService.findByName(AppConstants.FANTASY_CATEGORY)));
		controller.addNewBook(repeatBook);
		Iterable<Book> retrieveBooks = controller.retrieveBooks();
		
		List<Book> resultList = new ArrayList<>();
		retrieveBooks.forEach(resultList::add);
		
		long count = resultList.stream()
		        .filter(p -> p.getTitle().equals(AppConstants.JANE_EYRE_TITLE))
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
