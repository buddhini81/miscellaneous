package java8.buddhini.unit1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import java8.buddhini.unit1.PersonExampleUsingJava7.Condition;

public class PersonExampleUsingJava8 {
	public static void main(String[] args) {
		List<Person> people = Arrays.asList(
				new Person("Charls", "Dicksons", 60),
				new Person("Lewis", "Carroll", 42),
				new Person("Thomas", "Carlyle", 51),
				new Person("Charlotte", "Bronte", 45),
				new Person("Mathew", "Arnold", 39)
				);
		
		//Step 1 : Sort list by last name
		people.sort((p1,p2) -> p1.getLastName().compareTo(p2.getLastName()));
		
		//Step 2 : Create a method that prints all elements in the list
		System.out.println("Print all people");
		System.out.println("----------------");
		printConditionally(people, p -> true);

				
		//Step 3 : Create a method that prints all people that have last name beginning with C
		System.out.println("Print all people that have last name beginning with C");
		System.out.println("-----------------------------------------------------");
		printConditionally(people, p -> p.getLastName().startsWith("C"));
		
	}
	
	public static void printConditionally(List<Person> ppl, Condition c) {
		for(Person p : ppl) {
			if (c.test(p)) {
				System.out.println(p);
			}
		}
	}

}
