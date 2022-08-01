package com.hertz.lending.config;

import java.util.ArrayList;
import java.util.Arrays;
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
import com.hertz.lending.util.AppConstants;

/**
 * 
 * Initial class which implements ApplicationListener to inject some initial
 * data onto the H2 in-memory database
 * 
 * @author Bruno Franco
 *
 */
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

	/**
	 * Inserts categories, books and people onto the H2 DB
	 */
	public void onApplicationEvent(ContextRefreshedEvent event) {
		LOGGER.info("** Inserting initial Data onto the in-memory DB **");
		insertCategories();
		insertBooks();
		insertPeople();
	}

	private void insertCategories() {
		List<Category> categories = new ArrayList<>();
		Category romance = new Category();
		romance.setName(AppConstants.ROMANCE_CATEGORY);
		categories.add(romance);

		Category fantasy = new Category();
		fantasy.setName(AppConstants.FANTASY_CATEGORY);
		categories.add(fantasy);

		Category western = new Category();
		western.setName(AppConstants.WESTERN_CATEGORY);
		categories.add(western);

		categoryRepo.saveAll(categories);

		categoryRepo.findAll().forEach(b -> LOGGER.info("Category: " + b));
	}

	private void insertPeople() {
		List<Person> people = new ArrayList<>();
		Person peter = new Person();
		peter.setFirstName(AppConstants.PERSON_FIRSTNAME_PETER);
		peter.setLastName("Jackson");
		people.add(peter);

		Person john = new Person();
		john.setFirstName(AppConstants.PERSON_FIRSTNAME_JOHN);
		john.setLastName("Penfold");
		people.add(john);

		Person mark = new Person();
		mark.setFirstName(AppConstants.PERSON_FIRSTNAME_MARK);
		mark.setLastName("Bloggs");
		people.add(mark);

		personRepo.saveAll(people);

		personRepo.findAll().forEach(b -> LOGGER.info("Person: " + b));
	}

	private void insertBooks() {
		List<Book> books = new ArrayList<>();
		Book harryPotter = new Book(AppConstants.HARRY_POTTER_TITLE, AppConstants.J_K_ROLLING,
				Arrays.asList(categoryRepo.findByName(AppConstants.FANTASY_CATEGORY)));
		books.add(harryPotter);

		Book lordOfTheRings = new Book(AppConstants.THE_LORD_OF_THE_RINGS_TITLE, AppConstants.J_R_R_TOLKIEN,
				Arrays.asList(categoryRepo.findByName(AppConstants.FANTASY_CATEGORY)));
		books.add(lordOfTheRings);

		Book twilight = new Book(AppConstants.TWILIGHT_TITLE, AppConstants.STHEPENIE_MEYER, Arrays.asList(categoryRepo.findByName(AppConstants.ROMANCE_CATEGORY)));
		books.add(twilight);

		Book greatGatsby = new Book(AppConstants.THE_GREAT_GATSBY_TITLE, AppConstants.F_SCOTT_FITZGERALD,
				Arrays.asList(categoryRepo.findByName(AppConstants.ROMANCE_CATEGORY)));
		books.add(greatGatsby);

		bookRepo.saveAll(books);

		bookRepo.findAll().forEach(b -> LOGGER.info("Book: " + b));
	}
}
