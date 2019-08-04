package java8.buddhini.unit2;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

import java8.buddhini.unit1.Person;

public class PersonExampleUsingJava8Enhanced2 {
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
		//Here we need to pass in the 3rd parameter whihc is the behaviour we want the method to perform
		performConditionally(people, p -> true, p -> System.out.println(p));

				
		//Step 3 : Create a method that prints all people that have last name beginning with C
		System.out.println("Print all people that have last name beginning with C");
		System.out.println("-----------------------------------------------------");
		performConditionally(people, p -> p.getLastName().startsWith("C"), p -> System.out.println(p));
		//Let's say we want to customize the printing here, and print only the last name without printing the whole object.
		//We could do that by customizing the Lambda expression (parameter 3) which represents the behavior as follows.
		//Notice that this way we do not need to touch the method implementation at all to get different behaviors.
		performConditionally(people, p -> p.getLastName().startsWith("C"), p -> System.out.println(p.getLastName()));
		
	}
	
	//Here, instead of doing the printing inside the method, we could pass the behavior to perform as a parameter to the method.
	//For this we can use the Consumer out of the box interface which has the accept method that takes in a generic type argument and returns nothing
	public static void performConditionally(List<Person> ppl, Predicate<Person> c, Consumer<Person> consumer) {
		for(Person p : ppl) {
			if(c.test(p)) {
				consumer.accept(p);
			}
		}
	}
}
