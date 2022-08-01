package com.hertz.lending.config;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.hertz.lending.model.Book;
import com.hertz.lending.model.Category;
import com.hertz.lending.model.Person;
import com.hertz.lending.repository.BookRepository;
import com.hertz.lending.repository.CategoryRepository;
import com.hertz.lending.repository.PersonRepository;

@Component
public class InitialDataInserter implements ApplicationListener<ContextRefreshedEvent> {
	
	public static final Logger LOGGER = LoggerFactory.getLogger(InitialDataInserter.class);
	
	private BookRepository bookRepo;
	
	private PersonRepository personRepo;
	
	private CategoryRepository categoryRepo;
	
	@Autowired
	public InitialDataInserter(BookRepository bookRepo, PersonRepository personRepo, CategoryRepository categoryRepo) {
		super();
		this.bookRepo = bookRepo;
		this.personRepo = personRepo;
		this.categoryRepo = categoryRepo;
	}

	public void onApplicationEvent(ContextRefreshedEvent event) {
		insertCategories();
		insertBooks();
		insertPeople();
	}

	private void insertCategories() {
		List<Category> categories = new ArrayList<>();
		Category romance = new Category();
		romance.setName("Romance");
		categories.add(romance);
		
		Category fantasy = new Category();
		fantasy.setName("Fantasy");
		categories.add(fantasy);
		
		Category western = new Category();
		western.setName("Western");
		categories.add(western);
		
		categoryRepo.saveAll(categories);
		
		categoryRepo.findAll().forEach(b -> System.out.println(b));
	}
	
	private void insertPeople() {
		List<Person> people = new ArrayList<>();
		Person peter = new Person();
		peter.setFirstName("Peter");
		peter.setLastName("Jackson");
		people.add(peter);
		
		Person john = new Person();
		john.setFirstName("John");
		john.setLastName("Penfold");
		people.add(john);
		
		Person mark = new Person();
		mark.setFirstName("Mark");
		mark.setLastName("Bloggs");
		people.add(mark);
		
		personRepo.saveAll(people);
		
		personRepo.findAll().forEach(b -> System.out.println(b));
	}

	private void insertBooks() {
		List<Book> books = new ArrayList<>();
		Book harryPotter = new Book("Harry Potter and the Philosophical Stone", "J. K. Rolling", categoryRepo.findByName("Fantasy"));
		books.add(harryPotter);
		
		Book lordOfTheRings = new Book("The Lord of the Rings", "J. R. R. Tolkien", categoryRepo.findByName("Fantasy"));
		books.add(lordOfTheRings);
		
		Book twilight = new Book("Twilight", "Sthepenie Meyer", categoryRepo.findByName("Romance"));
		books.add(twilight);
		
		bookRepo.saveAll(books);
		
		bookRepo.findAll().forEach(b -> System.out.println(b));
	}
}
