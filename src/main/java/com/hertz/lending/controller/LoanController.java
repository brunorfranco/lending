package com.hertz.lending.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

	@PostMapping("/loans")
	Loan loanBook(@RequestBody Loan loan) {
		return loanService.record(loan);
	}
	
	@DeleteMapping("/loans/{id}")
	public void returnBook(@PathVariable Long id) {
		loanService.remove(loanService.findById(id));
	}

}
