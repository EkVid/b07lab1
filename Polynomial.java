import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;



public class Polynomial {
    double[] polynomial;
    int[] exponent;
    
    public Polynomial(){
    	polynomial = new double[1]; 
    	polynomial[0] = 0;
    	exponent = new int[1];
    	exponent[0] = 0;
    } 

    public Polynomial(double[] coef, int[] expo){
        polynomial = new double[coef.length];
        exponent = new int[coef.length];
        for(int i = 0; i < polynomial.length; i++){
            polynomial[i] = coef[i];
            exponent[i] = expo[i];
        }
    }
    
    public Polynomial(File f) throws IOException{
    	BufferedReader input = new BufferedReader(new FileReader(f));
    	String line = input.readLine();
    	String [] parts = line.split("(?=[+-])");

    	int length = parts.length;
    	exponent = new int[length];
    	polynomial = new double[length];
    	
    	int z = 0;
    	for (String i:parts) {
    		String[] more = i.split("x");
    		if(more.length == 1) {
    			polynomial[z] = Double.parseDouble(more[0]);
    			z++;
    		}
    		else {
    			polynomial[z] = Double.parseDouble(more[0]);
    			exponent[z] = Integer.parseInt(more[1]);
    			z++;
    		}
    	}
    	input.close();
    }

    public Polynomial add(Polynomial other){
    	
    	   int len = exponent.length + other.exponent.length;
           double[] resultPolynomial = new double[len];
           int[] resultExponent = new int[len];

           int index = 0;
           int i = 0;
           int j = 0;

           while (i < exponent.length && j < other.exponent.length) {
               if (exponent[i] == other.exponent[j]) {
                   double sum = polynomial[i] + other.polynomial[j];
                   if (sum != 0) {  
                       resultPolynomial[index] = sum;
                       resultExponent[index] = exponent[i];
                       index++;
                   }
                   i++;
                   j++;
               } else if (exponent[i] < other.exponent[j]) {
                   resultPolynomial[index] = polynomial[i];
                   resultExponent[index] = exponent[i];
                   index++;
                   i++;
               } else {
                   resultPolynomial[index] = other.polynomial[j];
                   resultExponent[index] = other.exponent[j];
                   index++;
                   j++;
               }
           }

           // Copy remaining terms from the longer polynomial
           while (i < exponent.length) {
               resultPolynomial[index] = polynomial[i];
               resultExponent[index] = exponent[i];
               index++;
               i++;
           }

           while (j < other.exponent.length) {
               resultPolynomial[index] = other.polynomial[j];
               resultExponent[index] = other.exponent[j];
               index++;
               j++;
           }

           // Resize the arrays to remove trailing zeros
           resultPolynomial = Arrays.copyOf(resultPolynomial, index);
           resultExponent = Arrays.copyOf(resultExponent, index);

           Polynomial sum = new Polynomial();
           sum.polynomial = resultPolynomial;
           sum.exponent = resultExponent;

           return sum;
    }
    
    
    
    public double evaluate(double x){
        double result = 0;
        for(int i = 0; i < polynomial.length; i++){
        	result += polynomial[i] * (Math.pow(x, exponent[i]));
        }
        return result;
    }
    
    public boolean hasRoot(double value){
        if(evaluate(value) == 0){
            return true;
        }
        return false;
    }
    
    public Polynomial multiply(Polynomial pol) {
        int len = polynomial.length * pol.polynomial.length;
        double[] new_coef = new double[len];
        int[] new_expo = new int[len];

        int x = 0;

        for (int i = 0; i < polynomial.length; i++) {
            for (int j = 0; j < pol.polynomial.length; j++) {
                new_coef[x] = polynomial[i] * pol.polynomial[j];
                new_expo[x] = exponent[i] + pol.exponent[j];
                x++;
            }
        }

        double[] final_coef = new double[len];
        int[] final_expo = new int[len];
        int final_len = 0;

        for (int i = 0; i < len; i++) {
            if (new_coef[i] != 0) {
                boolean hasRedundantExponent = false;
                for (int j = 0; j < final_len; j++) {
                    if (new_expo[i] == final_expo[j]) {
                        final_coef[j] += new_coef[i];
                        hasRedundantExponent = true;
                        break;
                    }
                }
                if (!hasRedundantExponent) {
                    final_coef[final_len] = new_coef[i];
                    final_expo[final_len] = new_expo[i];
                    final_len++;
                }
            }
        }

        final_coef = Arrays.copyOf(final_coef, final_len);
        final_expo = Arrays.copyOf(final_expo, final_len);

        return new Polynomial(final_coef, final_expo);
    }
    
    public void saveToFile(String file_name) throws FileNotFoundException {
    	// assume we can overwrite it?
    	PrintStream ps = new PrintStream(file_name);
    	String to_write = polynomial[0] + "x" + exponent[0];
    	for(int i = 1; i < polynomial.length; i++) {
    			if(polynomial[i] > 0) {
    				to_write = to_write + ( "+" + polynomial[i] + "x" + exponent[i]);
    			}
    			else{
    				to_write = to_write + (polynomial[i] + "x" + exponent[i]);
    			}
    		}
    	ps.println(to_write);
    	ps.close();
    }
}





