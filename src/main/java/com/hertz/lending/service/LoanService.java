package com.hertz.lending.service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hertz.lending.AppConstants;
import com.hertz.lending.exception.MaxNoLoansExceededException;
import com.hertz.lending.exception.OutstandingReturnException;
import com.hertz.lending.exception.UnsuccessfulLoanException;
import com.hertz.lending.model.Loan;
import com.hertz.lending.model.Person;
import com.hertz.lending.repository.LoanRepository;

@Service
public class LoanService {

	private LoanRepository loanRepo;

	@Autowired
	public LoanService(LoanRepository loanRepo) {
		super();
		this.loanRepo = loanRepo;
	}

	@Transactional
	public Loan lendBook(Loan loan)
			throws MaxNoLoansExceededException, UnsuccessfulLoanException, OutstandingReturnException {
		calculateReturnDate(loan);
		if (personHasNoOutstandingReturns(loan.getPerson()) && canPersonLoanMoreBooks(loan.getPerson())) {
			return loanRepo.save(loan);
		} else {
			throw new UnsuccessfulLoanException();
		}
	}

	@Transactional
	public Loan updateLoan(Loan loan) {
		return loanRepo.save(loan);
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

	/**
	 * This is only a good idea if all the clients interacting with this API have
	 * the same return time If they differ, then the returnDate should come already
	 * populate from the API clients
	 * 
	 * @param loan
	 */
	private void calculateReturnDate(Loan loan) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, AppConstants.RETURN_DAYS);
		loan.setReturnDate(calendar.getTime());
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

	private boolean canPersonLoanMoreBooks(Person person) throws MaxNoLoansExceededException {
		Example<Loan> example = Example.of(new Loan(null, person));
		List<Loan> loansPerPerson = loanRepo.findAll(example);

		if (loansPerPerson.size() >= AppConstants.MAX_NO_LOANS_PER_PERSON) {
			throw new MaxNoLoansExceededException();
		}
		return true;
	}

}
