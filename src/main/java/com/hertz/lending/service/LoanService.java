package com.hertz.lending.service;

import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hertz.lending.exception.MaxNoLoansExceededException;
import com.hertz.lending.exception.OutstandingReturnException;
import com.hertz.lending.exception.UnsuccessfulLoanException;
import com.hertz.lending.model.Loan;
import com.hertz.lending.repository.LoanRepository;
import com.hertz.lending.util.LoanUtils;
import com.hertz.lending.validator.LoanValidator;

@Service
public class LoanService {

	private LoanRepository loanRepo;

	private LoanValidator loanValidator;

	@Autowired
	public LoanService(LoanRepository loanRepo, LoanValidator loanValidator) {
		super();
		this.loanRepo = loanRepo;
		this.loanValidator = loanValidator;
	}

	@Transactional
	public Loan lendBook(Loan loan)
			throws MaxNoLoansExceededException, UnsuccessfulLoanException, OutstandingReturnException {
		LoanUtils.calculateReturnDate(loan);
		if (loanValidator.isValidLoan(loan.getPerson())) {
			return loanRepo.save(loan);
		} else {
			throw new UnsuccessfulLoanException();
		}
	}

	@Transactional
	public Loan updateLoan(Loan loan) {
		if(loan.getId() == null) {
			throw new ObjectNotFoundException(Loan.class, "Loan");
		}
		Optional<Loan> foundLoan = loanRepo.findById(loan.getId());
		if(foundLoan.isPresent()) {
			return loanRepo.save(loan);
		} else {
			throw new ObjectNotFoundException(Loan.class, "Loan");
		}
	}

	public Optional<Loan> findById(Long id) {
		return loanRepo.findById(id);
	}

	@Transactional
	public void returnBook(Optional<Loan> optional) {
		if (optional.isPresent()) {
			loanRepo.delete(optional.get());
		}
	}

	@Transactional
	public void returnAllBooks() {
		loanRepo.deleteAll();
	}

	public Iterable<Loan> findAll() {
		return loanRepo.findAll();
	}

}
