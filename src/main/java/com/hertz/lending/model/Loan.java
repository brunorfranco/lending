package com.hertz.lending.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class Loan {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@OneToOne(optional = false, fetch = FetchType.EAGER)
	private Book book;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	private Person person;
	
	private Date returnDate;

	public Loan() {
		super();
	}

	public Loan(Book book, Person person) {
		super();
		this.book = book;
		this.person = person;
	}

}
