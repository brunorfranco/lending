package com.hertz.lending.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class Loan {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@OneToOne(optional = false)
	private Book book;

	@OneToOne(optional = false)
	private Person person;

	public Loan() {
		super();
	}

	public Loan(Book book, Person person) {
		super();
		this.book = book;
		this.person = person;
	}

}
