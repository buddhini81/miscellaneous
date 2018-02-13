package java8.buddhini.unit3;

import java.util.Arrays;
import java.util.List;

import java8.buddhini.unit1.Person;

public class StreamsExample1 {
	public static void main(String[] args) {
		List<Person> people = Arrays.asList(
				new Person("Charls", "Dicksons", 60), 
				new Person("Lewis", "Carroll", 42),
				new Person("Thomas", "Carlyle", 51), 
				new Person("Charlotte", "Bronte", 45),
				new Person("Mathew", "Arnold", 39));
		
		
		//Say that you need to print the people who's last name starts with the letter 'C'.
		//First you add the list of people to a conveyer belt by saying people.stream().
		//Next you do the operation of filtering for the people who's last name starts with C and removing the rest of the people out from the list.
		//The remaining list of people are now past on to the next operation in the assembly line which is printing..
		//The forEach with go through the people in the list and print their first names in to the console.
		//This is a very powerful way of processing a collection as it allows to perform multiple operations to a collection and at each operation
		//it does some modification to the collection before anding it over to the next operation.
		people.stream()
		.filter(p -> p.getLastName().startsWith("C"))
		.forEach(p -> System.out.println(p.getFirstName())); //This is the terminal operation
		
		//Another example
		long count = people.stream()
		.filter(p -> p.getLastName().startsWith("C"))
		.count(); //This is the terminal operation
		
		System.out.println(count);
		
		//In Java 8 we can leverage the feature of parallel processing on a collection
		//by creating a parallel stream of a collection by using the parallelStream method.
		//This will create a possibly parallel stream of the collection where a part of the collection would be processed
		//by one thread/process and another part of the collection will be processed in parallel by another thread/process.
		//This would greatly increase the efficiency of processing a very large collection.
		long parallelCount = people.parallelStream()
				.filter(p -> p.getLastName().startsWith("C"))
				.count(); //This is the terminal operation
				
		System.out.println(parallelCount);
		
	}
}
