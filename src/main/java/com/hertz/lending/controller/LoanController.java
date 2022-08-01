package com.hertz.lending.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hertz.lending.exception.MaxNoLoansExceededException;
import com.hertz.lending.exception.OutstandingReturnException;
import com.hertz.lending.exception.UnsuccessfulLoanException;
import com.hertz.lending.model.Loan;
import com.hertz.lending.service.LoanService;

@RestController
public class LoanController {

	private LoanService loanService;

	@Autowired
	public LoanController(LoanService loanService) {
		super();
		this.loanService = loanService;
	}

	@GetMapping("/loans")
	public Iterable<Loan> retrieveLoans() {
		return loanService.findAll();
	}

	@PostMapping("/loans")
	Loan lendBook(@RequestBody Loan loan)
			throws MaxNoLoansExceededException, UnsuccessfulLoanException, OutstandingReturnException {
		return loanService.lendBook(loan);
	}
	
	@PutMapping("/loans")
	Loan updateLoan(@RequestBody Loan loan){
		return loanService.updateLoan(loan);
	}

	@DeleteMapping("/loans/{id}")
	public void returnBook(@PathVariable Long id) {
		loanService.returnBook(loanService.findById(id));
	}

	@DeleteMapping("/loans")
	public void returnAllBook() {
		loanService.returnAllBooks();
	}

}
