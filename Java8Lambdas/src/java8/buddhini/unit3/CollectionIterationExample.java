package java8.buddhini.unit3;

import java.util.Arrays;
import java.util.List;

import java8.buddhini.unit1.Person;

public class CollectionIterationExample {
	
	public static void main(String[] args) {
		List<Person> people = Arrays.asList(
				new Person("Charls", "Dicksons", 60),
				new Person("Lewis", "Carroll", 42),
				new Person("Thomas", "Carlyle", 51),
				new Person("Charlotte", "Bronte", 45),
				new Person("Mathew", "Arnold", 39)
				);
		
		System.out.println("Iterating using the for loop");
		for(int i = 0; i < people.size(); i++) {
			System.out.println(people.get(i));
		}
		
		System.out.println("Iterating using the for in loop");
		for(Person p : people) {
			System.out.println(p);
		}
		
		//Both the above loops does external iteration.
		//The developer controls the iteration. We tell the java runtime to print the elements in the list sequentially
		
		//Below iteration is internal iteration
		//We do not care about how the iteration happens. It can happen either sequentially or parallely internally.
		System.out.println("Iterating using the Lambda for loop");
		people.forEach(p -> System.out.println(p));
		people.forEach(System.out::println); //using method reference
	}

}
