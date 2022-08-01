package com.hertz.lending.repository;

import org.springframework.data.repository.CrudRepository;

import com.hertz.lending.model.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {

	Category findByName(String name);
}
