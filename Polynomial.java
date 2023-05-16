
public class Polynomial {
    double[] polynomial;

    public Polynomial(){
    	polynomial = new double[1]; 
    	polynomial[0] = 0;
    } 

    public Polynomial(double[] array){
        polynomial = new double[array.length];
        for(int i = 0; i < polynomial.length; i++){
            polynomial[i] = array[i];
        }
    }

    public Polynomial add(Polynomial other){
    	
    	int len = Math.max(polynomial.length, other.polynomial.length);
    	double[] res = new double[len];
 
    	Polynomial output = new Polynomial(polynomial);

        for(int i = 0; i < len; i++){
        	if(i >= other.polynomial.length) {
        		res[i] = output.polynomial[i];
        	}
        	else if (i >= output.polynomial.length){
        		res[i] = other.polynomial[i];
        	}
            
        	else {
        		res[i] = other.polynomial[i] + output.polynomial[i];
        	}
        }
        
        Polynomial result = new Polynomial(res);
        
        return result;
    }
    
    public double evaluate(double x){
        double result = 0;
        for(int i = 0; i < polynomial.length; i++){
            result += polynomial[i]* (Math.pow(x,i)); 
        }
        return result;
    }

    public boolean hasRoot(double value){
        if(evaluate(value) == 0){
            return true;
        }
        return false;
    }
}
