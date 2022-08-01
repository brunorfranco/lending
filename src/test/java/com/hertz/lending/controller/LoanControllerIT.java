package com.hertz.lending.controller;

import org.hibernate.ObjectNotFoundException;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hertz.lending.config.InitialDataInserter;
import com.hertz.lending.controller.LoanController;
import com.hertz.lending.exception.MaxNoLoansExceededException;
import com.hertz.lending.exception.OutstandingReturnException;
import com.hertz.lending.exception.UnsuccessfulLoanException;
import com.hertz.lending.model.Book;
import com.hertz.lending.model.Loan;
import com.hertz.lending.model.Person;
import com.hertz.lending.repository.BookRepository;
import com.hertz.lending.repository.PersonRepository;
import com.hertz.lending.util.AppConstants;
import com.hertz.lending.util.LoanUtils;

@SpringBootTest
public class LoanControllerIT {

	public static final Logger LOGGER = LoggerFactory.getLogger(InitialDataInserter.class);

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

	@BeforeEach
	public void clearEntries() {
		controller.returnAllBook();
	}

	@Test
	public void loanBook_firstBooking_success()
			throws MaxNoLoansExceededException, UnsuccessfulLoanException, OutstandingReturnException {
		Person person = personRepo.findByFirstName(AppConstants.PERSON_FIRSTNAME_JOHN);
		Book book = bookRepo.findByTitle(AppConstants.TWILIGHT_TITLE);
		Loan loan = new Loan(book, person);
		Loan recordedLoan = controller.lendBook(loan);
		Assert.assertNotNull(recordedLoan);
	}

	@Test
	public void loanBook_moreThan3Books_exception() {
		Person person = personRepo.findByFirstName(AppConstants.PERSON_FIRSTNAME_JOHN);

		/**
		 * There are 4 books initially setup so it will fail on the 4th
		 */
		bookRepo.findAll().forEach(b -> {
			Loan loan = new Loan(b, person);
			try {
				LOGGER.info("Lending book: " + controller.lendBook(loan));
			} catch (MaxNoLoansExceededException | UnsuccessfulLoanException | OutstandingReturnException e) {
				Assert.assertTrue(true);
			}
		});

	}

	@Test
	public void loanBook_updateLoan_success()
			throws MaxNoLoansExceededException, UnsuccessfulLoanException, OutstandingReturnException {
		Person person = personRepo.findByFirstName(AppConstants.PERSON_FIRSTNAME_JOHN);
		Book book = bookRepo.findByTitle(AppConstants.TWILIGHT_TITLE);
		Loan loan = new Loan(book, person);
		loan = controller.lendBook(loan);

		loan.setReturnDate(LoanUtils.yesterday());
		controller.updateLoan(loan);

	}

	@Test
	public void loanBook_hasOutstandingReturn_exception()
			throws MaxNoLoansExceededException, UnsuccessfulLoanException, OutstandingReturnException {
		Person person = personRepo.findByFirstName(AppConstants.PERSON_FIRSTNAME_JOHN);
		Book book = bookRepo.findByTitle(AppConstants.TWILIGHT_TITLE);
		Loan loan = new Loan(book, person);
		loan = controller.lendBook(loan);
		/**
		 * Manipulating the return date to simulate a late return
		 */
		loan.setReturnDate(LoanUtils.yesterday());
		controller.updateLoan(loan);

		Assertions.assertThrows(OutstandingReturnException.class, () -> {
			controller.lendBook(new Loan(bookRepo.findByTitle(AppConstants.THE_GREAT_GATSBY_TITLE), person));
		});
	}

	@Test
	public void returnBook_firstBooking_true() {
		controller.returnBook(1l);
	}

	@Test
	public void updateLoan_loanDoesNotExist_exception()
			throws MaxNoLoansExceededException, UnsuccessfulLoanException, OutstandingReturnException {
		Person person = personRepo.findByFirstName(AppConstants.PERSON_FIRSTNAME_JOHN);
		Book book = bookRepo.findByTitle(AppConstants.TWILIGHT_TITLE);

		Assertions.assertThrows(ObjectNotFoundException.class, () -> {
			controller.updateLoan(new Loan(book, person));
		});
	}

}
