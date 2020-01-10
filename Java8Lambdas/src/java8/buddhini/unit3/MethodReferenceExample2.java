package java8.buddhini.unit3;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

import java8.buddhini.unit1.Person;

public class MethodReferenceExample2 {
	
	public static void main(String[] args) {
		List<Person> people = Arrays.asList(
				new Person("Charls", "Dicksons", 60),
				new Person("Lewis", "Carroll", 42),
				new Person("Thomas", "Carlyle", 51),
				new Person("Charlotte", "Bronte", 45),
				new Person("Mathew", "Arnold", 39)
				);
		
		
		System.out.println("Print all people");
		System.out.println("----------------");
		//The 3rd parameter a Lambda expression which can be replaced by a method reference as follows,
		//If a method takes one argument and does some processing using that argument like below:
		//The Java compiler knows that the 3rd argument is a Consumer and it expects one argument with which it will do some processing in the test method.
		//The processing that it does is printing the argument to the console. Since the println method isn't a static method, it has to be invoked using a 
		//reference/object (i.e. System.out). Hence the method reference syntax would be object::method
		performConditionally(people, p -> true, System.out::println);

		
	}

	public static void performConditionally(List<Person> ppl, Predicate<Person> c, Consumer<Person> consumer) {
		for(Person p : ppl) {
			if(c.test(p)) {
				consumer.accept(p);
			}
		}
	}
}