import java.io.File;
import java.io.IOException;

public class Driver {
	public static void main(String [] args) throws IOException {
		
		Polynomial p = new Polynomial();
		System.out.println(p.evaluate(3));
		System.out.println("\n");
		double [] c1 = {1,-1,2,3,4};
		int [] e1 = {1,2,4,5,1};
		Polynomial p1 = new Polynomial(c1, e1);	
		double [] c2 = {1,-1,5,6};
		int [] e2 = {0,1,-1,2};
		Polynomial p2 = new Polynomial(c2, e2);
		Polynomial p3 = p1.add(p2);
		if(p3.evaluate(1) == 20) {
			System.out.println("The value outputed is correct");
		}
		else {
			System.out.println("The value outputed is wrong, unless you modified the polynomial");
		}	

		System.out.println("\n");
		
		double [] d1 = {1,-1};
		int [] f1 = {1,2};
		Polynomial m1 = new Polynomial(d1, f1);	
		double [] d2 = {1,3};
		int [] f2 = {0,1};
		Polynomial m2 = new Polynomial(d2, f2);
		
		Polynomial x = m1.multiply(m2);
		
		if(x.evaluate(2) == -14) {
			System.out.println("The value outputed is correct");
		}
		else {
			System.out.println("The value outputed is wrong, unless you modified the polynomial");
		}	
		
		System.out.println("\n");
		
		
		// test whether reads the correct polynomial from the file
		File f = new File("test_file");
		Polynomial m = new Polynomial(f);
		if(m.evaluate(1) == 16) {
			System.out.println("The value outputed is correct");
		}
		else {
			System.out.println("The value outputed is wrong, unless you modified the polynomial");
		}	
		
		// test if the file has the correct output
		x.saveToFile("output");
		
	}

}
