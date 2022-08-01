package com.hertz.lending.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
	Loan recordNewLoans(@RequestBody Loan loan) {
		return loanService.record(loan);
	}

}
