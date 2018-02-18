package java8.buddhini.unit2;

public class ThisReferenceAndLambdas {

	public static void main(String[] args) {
		ThisReferenceAndLambdas thisReferenceAndLambdas = new ThisReferenceAndLambdas();
		//In the anonymous inner class the "this" refers to the Process object.
		//Hence it is overridden
		thisReferenceAndLambdas.doProcess(10, new Process() {

			@Override
			public void process(int i) {
				System.out.println("The value of i is " + i);
				System.out.println(this);
			}
			
			public String toString() {
				return "This is the annonympus inner class";
			}
			
			
		});
		

		/*thisReferenceAndLambdas.doProcess(10, i -> {
			System.out.println("The value of i is " + i);
			//System.out.println(this); //This will not work
	     });*/
		
		thisReferenceAndLambdas.execute();
	}
	
	public void execute() {
		//In the Lambda expression, the Lambda express inside of an instance method, the "this" refers to the 
		//object that the method was invoked upon.
		doProcess(10, i -> {
			System.out.println("The value of i is " + i);
			System.out.println(this);
		});
	}
	
	public void doProcess(int i, Process p) {
		p.process(i);
	}

	@Override
	public String toString() {
		return "This is the ThisReferenceAndLambdas class";
	}
	
	
}

interface Process {
	public void process(int i);
}
