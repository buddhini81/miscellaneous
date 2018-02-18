package java8.buddhini.unit2;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import java8.buddhini.unit1.Person;

public class PersonExampleUsingJava8Enhanced {
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
	
	//Here we could use the out of the box interface Predicate instead of defining the Condition interface
	//Notice here that we did not have to change anything in the method call since the method is satisfied as long as an 
	//implementation to the interface is provided by the Lambda expression that is passed to it and the Lambda expression does not care which interface it is implementing.
	public static void printConditionally(List<Person> ppl, Predicate<Person> c) {
		for(Person p : ppl) {
			if(c.test(p)) {
				System.out.println(p);
			}
		}
	}

}
