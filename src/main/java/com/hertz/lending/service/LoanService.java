package com.hertz.lending.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hertz.lending.model.Loan;
import com.hertz.lending.repository.LoanRepository;

@Service
public class LoanService {

	private LoanRepository loanRepo;

	@Autowired
	public LoanService(LoanRepository loanRepo) {
		super();
		this.loanRepo = loanRepo;
	}

	public Loan record(Loan loan) {
		return loanRepo.save(loan);
	}

	public Optional<Loan> findById(Long id) {
		return loanRepo.findById(id);
	}
	
	public void remove(Optional<Loan> optional) {
		if(optional.isPresent()) {
			loanRepo.delete(optional.get());
		}
	}
	
}
