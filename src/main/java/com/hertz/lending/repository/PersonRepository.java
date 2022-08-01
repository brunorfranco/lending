package com.hertz.lending.repository;

import org.springframework.data.repository.CrudRepository;

import com.hertz.lending.model.Person;

public interface PersonRepository extends CrudRepository<Person, Long>{
	
	Person findByFirstName(String firstName);
}

