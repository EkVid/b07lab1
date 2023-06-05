import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Driver {
	public static void main(String [] args) throws IOException {
		
		Polynomial p = new Polynomial();
		System.out.println(p.evaluate(3));
		double [] c1 = {1,-1};
		int [] e1 = {1,2};
		Polynomial p1 = new Polynomial(c1, e1);	
		double [] c2 = {1,-1};
		int [] e2 = {0,1};
		Polynomial p2 = new Polynomial(c2, e2);

		System.out.println("\n");
		
		Polynomial x = p1.multiply(p2);
		
//		System.out.println("expo len = " + x.exponent.length);
//		System.out.println("\n");
//		System.out.println("coef len = " + x.polynomial.length);


//		for (int i = 0; i < x.polynomial.length; i++) {
//			System.out.println(x.polynomial[i]);
//		}
//		System.out.println("\n");
//		
//		for (int i = 0; i < x.exponent.length; i++) {
//			System.out.println(x.exponent[i]);
//		}
		File f = new File("new_file");
		Polynomial m = new Polynomial(f);
		System.out.println(Arrays.toString(m.polynomial));
		System.out.println(Arrays.toString(m.exponent));
		
		
	}

}