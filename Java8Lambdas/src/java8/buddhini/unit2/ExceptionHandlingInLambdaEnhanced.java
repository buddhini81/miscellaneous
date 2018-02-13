package java8.buddhini.unit2;

import java.util.function.BiConsumer;

public class ExceptionHandlingInLambdaEnhanced {
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
		//Instead of using try catch inside the Lambda, we could do it outside by moving it inside a wrapper that accepts this Lambda
		//The wrapper Lambda will take care of handling the exception so the original Lambda expression will remain clean.
		perform(numbers, key, wrapperLambda((v,k) -> System.out.println(v / k)));
		
	}

	//I can pass the behavior/arithmetic operation to be done instead of hard coding it inside the function
	//using the out of the box interface BiConsumer. It has an accept method that takes in two arguments and performs an operation on them
	private static void perform(int[] n, int key, BiConsumer<Integer, Integer> consumer) {
		for(int i : n) {
			consumer.accept(i, key);
		}
	}
	
	//Create a wrapper Lambda that accepts a Lambda and returns the same type - BiConsumer (A Lambda expression that implements the accept method of the BiConsumer)
	private static BiConsumer<Integer, Integer> wrapperLambda(BiConsumer<Integer, Integer> consumer) {
		return (v,k) -> {
		  try {
			consumer.accept(v, k);
		  } catch(ArithmeticException e) {
			  System.out.println("Exception Cought inside wrapper!");
		  }
		};
	}
}
