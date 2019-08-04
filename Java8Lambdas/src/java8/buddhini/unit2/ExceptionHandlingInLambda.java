package java8.buddhini.unit2;

import java.util.function.BiConsumer;

public class ExceptionHandlingInLambda {

	public static void main(String[] args) {
		int[] numbers = {10,20,30,40,50};
		int key = 0;
		
		//I need to write a function that would take in the array of numbers and performs some 
		//arithmetic operation to them using the key that is also passed to it.
		//Suppose I want to add the key to each number in the array
		perform(numbers, key, (v,k) -> System.out.println(v + k));
		
	    //Suppose I want to divide each number in the array by the key
		//in case I make the key as 0, this will throw and Exception
		//Handle the exception in the Lambda expression
		perform(numbers, key, (v,k) -> {
			try {
				System.out.println(v / k);
			} catch(ArithmeticException e) {
				System.out.println("Arithmetic Exception Occured!");
			}
		});
		
		
	}

	//I can pass the behavior/arithmetic operation to be done instead of hard coding it inside the function
	//using the out of the box interface BiConsumer. It has an accept method that takes in two arguments and performs an operation on them
	private static void perform(int[] n, int key, BiConsumer<Integer, Integer> consumer) {
		for(int i : n) {
			consumer.accept(i, key);
		}
	}
	

}
