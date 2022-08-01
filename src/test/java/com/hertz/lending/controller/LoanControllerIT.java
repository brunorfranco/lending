package com.hertz.lending.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hertz.lending.controller.LoanController;
import com.hertz.lending.model.Book;
import com.hertz.lending.model.Loan;
import com.hertz.lending.model.Person;
import com.hertz.lending.repository.BookRepository;
import com.hertz.lending.repository.PersonRepository;

@SpringBootTest
public class LoanControllerIT {

	private LoanController controller;

	private PersonRepository personRepo;

	private BookRepository bookRepo;

	@Autowired
	public LoanControllerIT(LoanController controller, PersonRepository personRepo, BookRepository bookRepo) {
		super();
		this.controller = controller;
		this.personRepo = personRepo;
		this.bookRepo = bookRepo;
	}

	@Before
	public void startup() {
	}

	@Test
	public void recordNewLoan_firstBooking_true() {
		Person person = personRepo.findByFirstName("John");
		Book book = bookRepo.findByTitle("Twilight");
		Loan loan = new Loan(book, person);
		Loan recordedLoan = controller.recordNewLoans(loan);
		Assert.assertNotNull(recordedLoan);
	}

}
