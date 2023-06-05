import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;



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
    }

    public Polynomial add(Polynomial other){
    	
    	int len = Math.max(polynomial.length, other.polynomial.length);
    	double[] res = new double[len];
    	int[] res_expo = new int[len];
 
    	Polynomial output = new Polynomial(polynomial, exponent);

        for(int i = 0; i < len; i++){
        	if(i >= other.polynomial.length) {
        		res[i] = output.polynomial[i];
        		res_expo[i] = output.exponent[i];
        	}
        	else if (i >= output.polynomial.length){
        		res[i] = other.polynomial[i];
          		res_expo[i] = other.exponent[i];
        	}
            
        	else {
        		res[i] = other.polynomial[i] + output.polynomial[i];
          		res_expo[i] = other.exponent[i] + output.exponent[i];
        	}
        }
        
        Polynomial result = new Polynomial(res, res_expo);
        
        return result;
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
    			if (polynomial[i] * pol.polynomial[j] != 0) {
    				new_coef[x] = polynomial[i] * pol.polynomial[j]; 	
    				new_expo[x] = exponent[i] + pol.exponent[j];
    			}
    			x++;
    		}
    	}
    	// what if coef = 0? Omit?
    	

    	int modi_len = 0;
    	for (int y = 0; y < len; y++) {
    		if (new_coef[y] != 0) {
    			modi_len++;
    		}
    	}

    	double[] modi_coef = new double[modi_len];
    	int[] modi_expo = new int[modi_len];
    	
    	int h = 0;
        for(int n = 0; n < len; n++) {
        	if(new_coef[n] != 0) {
        		modi_coef[h] = new_coef[n];
        		modi_expo[h] = new_expo[n];
        		h++;
        	}
        }
        
        // need to check redundant exponents
        for(int z = 0; z < modi_len; z++) {
        	for(int m = z+1; m < modi_len; m++) {
        		if(modi_expo[z] == modi_expo[m]) {
        			modi_expo[m] = 0;
        			modi_coef[z] += modi_coef[m];
        			modi_coef[m] = 0;
        		}
        	}
        }
        
        int final_len = 0;
        
        for(int n = 0; n < modi_len; n++){
        	if(modi_coef[n] != 0) {
        		final_len++;
        	}
        }
        
        double[] final_coef = new double[final_len];
    	int[] final_expo = new int[final_len];
    	
    	int k = 0;
    	for(int t = 0; t < modi_len; t++) {
    		if(modi_coef[t] != 0) {
    			final_coef[k] = modi_coef[t];
    			final_expo[k] = modi_expo[t];
    			k++;
    		}
    	}
    	
    	Polynomial product = new Polynomial(final_coef, final_expo);
    	return product;
    }
}





