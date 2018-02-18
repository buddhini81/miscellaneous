public class MathTest {
	public static void main(String[] args) {
		for(int i = 1; i < 11; i++) {
			System.out.print(i + (i == 10 ? "" : ","));
			if(i == 10) {
				System.out.print("\n");
				for(int j = 1; j < 11; j++) {
					System.out.print(j * j + (j == 10 ? "" : ","));
					if(j == 10) {
						System.out.print("\n");
						for(int k = 1; k < 11; k++) {
							System.out.print(k * k * k + (k == 10 ? "" : ","));
						}
					}
				}
			}
			
		}
	}
		
		
}
