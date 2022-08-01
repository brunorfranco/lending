package com.hertz.lending.validator;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import com.hertz.lending.exception.MaxNoLoansExceededException;
import com.hertz.lending.exception.OutstandingReturnException;
import com.hertz.lending.model.Loan;
import com.hertz.lending.model.Person;
import com.hertz.lending.repository.LoanRepository;
import com.hertz.lending.util.AppConstants;

@Component
public class LoanValidator {
	
	private LoanRepository loanRepo;

	@Autowired
	public LoanValidator(LoanRepository loanRepo) {
		super();
		this.loanRepo = loanRepo;
	}

	public boolean isValidLoan(Person person) throws OutstandingReturnException, MaxNoLoansExceededException {
		return personHasNoOutstandingReturns(person) && canPersonLoanMoreBooks(person);
	}
	
	private boolean personHasNoOutstandingReturns(Person person) throws OutstandingReturnException {
		Example<Loan> example = Example.of(new Loan(null, person));
		List<Loan> loansPerPerson = loanRepo.findAll(example);
		for (Loan loan : loansPerPerson) {
			if (loan.getReturnDate().before(Calendar.getInstance().getTime())) {
				throw new OutstandingReturnException();
			}
		}

		return true;
	}
	
	private boolean canPersonLoanMoreBooks(Person person) throws MaxNoLoansExceededException {
		Example<Loan> example = Example.of(new Loan(null, person));
		List<Loan> loansPerPerson = loanRepo.findAll(example);

		if (loansPerPerson.size() >= AppConstants.MAX_NO_LOANS_PER_PERSON) {
			throw new MaxNoLoansExceededException();
		}
		return true;
	}

}
