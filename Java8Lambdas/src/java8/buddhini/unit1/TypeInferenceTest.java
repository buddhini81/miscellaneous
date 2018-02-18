package java8.buddhini.unit1;

public class TypeInferenceTest {

	public static void main(String[] args) {
		
		StringLengthLambda lambda = (String s) -> s.length();
		
		//We can remove the data type of the parameter as well as the parenthesis to make the expression shorter.
		StringLengthLambda lambda2 = s -> s.length();
		
		//Passing the lambda expression to method as the argument
		int length = countStringLength(lambda2);
		System.out.println(length);
		
		//Passing the lambda expression in-line to the method. 
		int length2= countStringLength(s -> s.length());
		System.out.println(length2);
		
		//The doCalculation method expects an implementation of the MathLambda interface.
		//Therefore, the java compiler infers data types of x & y parameters as integers and the return type as an integer by looking inside MathLambda interface's add method
		int ans = doCalculation((x,y) -> x + y);
		System.out.println(ans);
	}
	
	public static int countStringLength(StringLengthLambda expr) {
		return expr.getLength("Hello World!");
	}
	
	public static int doCalculation(MathLambda expr) {
		return expr.add(10, 20);
	}
	
	
	interface StringLengthLambda {
		public int getLength(String s);
	}
}
