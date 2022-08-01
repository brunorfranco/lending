package com.hertz.lending.repository;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.repository.CrudRepository;

import com.hertz.lending.model.Book;
import com.hertz.lending.model.Loan;
import com.hertz.lending.model.Person;

public interface LoanRepository extends CrudRepository<Loan, Long>{
	
	Loan findByBook(Book book);
	List<Loan> findByPerson(Person person);
	List<Loan> findAll(Example<Loan> example);
}
