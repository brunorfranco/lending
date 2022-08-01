package com.hertz.lending.predicate;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.hertz.lending.model.Book;

public abstract class BookPredicates {
	
	public static Predicate<Book> hasTitle(String title) {
		return p -> p.getTitle() == title;
	}

	public static List<Book> filterBooks(List<Book> books, Predicate<Book> predicate) {
		return books.stream().filter(predicate).collect(Collectors.<Book>toList());
	}

}
