package java8.buddhini.unit1;

public class LambdaThreadExample {
	
	public static void main(String[] args) {
		//Implementing the Runnable interface using an anonymous inner class.
		Thread myThread = new Thread(new Runnable() {
			
			public void run() {
				System.out.println("From inside anonymous inner class's run method.");
			}
		});
		
		myThread.run();
		
		//Implementing the Runnable interface using a lambda expression.
		Thread myThread2 = new Thread(()-> System.out.println("From inside the Lambda expression"));
		myThread2.run();
	}
}
