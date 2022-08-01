package com.hertz.lending.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hertz.lending.model.Category;
import com.hertz.lending.repository.CategoryRepository;

@Service
public class CategoryService {

	private CategoryRepository categoryRepo;

	@Autowired
	public CategoryService(CategoryRepository categoryRepo) {
		super();
		this.categoryRepo = categoryRepo;
	}

	public Category add(Category category) {
		return categoryRepo.save(category);
	}
	
	public void remove(Optional<Category> optional) {
		if(optional.isPresent()) {
			categoryRepo.delete(optional.get());
		}
	}
	
	public Optional<Category> findById(Long id) {
		return categoryRepo.findById(id);
	}
	
	public Category findByName(String name) {
		return categoryRepo.findByName(name);
	}
	
	public Iterable<Category> findAll() {
		return categoryRepo.findAll();
	}
	
}
