package com.hertz.lending;

import java.io.IOException;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hertz.lending.model.Book;
import com.hertz.lending.model.Category;
import com.hertz.lending.model.Loan;
import com.hertz.lending.model.Person;

public class JavaToJsonUtil {
	
	@Test
	public void javaToJsonUtilMethod() {
		ObjectMapper Obj = new ObjectMapper();
		Book book = new Book("The House of Mirth", "Edith Wharton", Arrays.asList(new Category("Western")));
        try {  
            String jsonStr = Obj.writeValueAsString(book);  
            System.out.println(jsonStr);  
        }  
        catch (IOException e) {  
            e.printStackTrace();  
        }  
        
        Person person = new Person();
        person.setId(4l);
        person.setFirstName("Peter");
        person.setLastName("Jackson");
        Loan loan = new Loan(book, person);
        try {  
            String jsonStr = Obj.writeValueAsString(loan);  
            System.out.println(jsonStr);  
        }  
        catch (IOException e) {  
            e.printStackTrace();  
        }  
	}

}
