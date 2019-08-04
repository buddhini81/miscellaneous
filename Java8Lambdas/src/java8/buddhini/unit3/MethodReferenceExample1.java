package java8.buddhini.unit3;

public class MethodReferenceExample1 {

	public static void main(String[] args) {
		Thread t = new Thread(() -> processMessage());// This Lambda expression can be replaced with a method reference
		t.start();
		
		//If the Lambda implements a method that takes in no arguments and it does not process any arguments
		//We can use the following method reference in place of the Lambda expression.
		//ClassName::MethodName - since the method is a static method
		Thread t2 = new Thread(MethodReferenceExample1::processMessage);
		t2.start();
	}
	
	public static void processMessage() {
		System.out.println("new thread");
	}
}
