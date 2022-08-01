package com.hertz.lending.repository;

import org.springframework.data.repository.CrudRepository;

import com.hertz.lending.model.Book;
import com.hertz.lending.model.Loan;
import com.hertz.lending.model.Person;

public interface LoanRepository extends CrudRepository<Loan, Long>{
	
	Loan findByBook(Book book);
	Loan findByPerson(Person person);
}
