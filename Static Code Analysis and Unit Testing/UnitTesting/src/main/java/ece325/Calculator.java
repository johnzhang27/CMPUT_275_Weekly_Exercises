package ece325;

/**
 * Assignment 4 Part 2: Unit Testing <br />
 * The calculator to run the test cases
 * @author YongQuan Zhang 1515873
 */
public // TODO: Assignment 4 Part 2 -- Create the Calculator here

class Calculator{
	/**
     * addition, subtraction, multiplication, division
     * @param a      {@code double} a double number to be calculated
	 * @param b      {@code double} a double number to be calculated
	 * @return 		 answer in Double type
     */
	public Double add(double a, double b){
		return a+b;
	}
	public Double subtract(double a, double b){
		return a-b;
	}
	public Double multiply(double a, double b){
		return a*b;
	}
	public Double divide(double a, double b){
		return a/b;
	}
	/**
     * Find solution of a quadratic equation
     * @param a      {@code double} a double number to be calculated
	 * @param b      {@code double} a double number to be calculated
	 * @param c      {@code double} a double number to be calculated
	 * @return 		 answer in Double type
     */
	public Double[] getRoots(double a, double b, double c){
		double root1, root2;
		Double[] ans = new Double[2];
		double determinant = b * b - 4 * a * c;
		root1 = (-b + Math.sqrt(determinant)) / (2 * a);
		root2 = (-b - Math.sqrt(determinant)) / (2 * a);

        if(determinant > 0 && root1 == root2) {
			ans = new Double[1];
			ans[0] = root1;
			return ans;
        }
        else if(determinant == 0) {
			root1 = root2 = -b / (2 * a);
			ans = new Double[1];
			ans[0] = root1;
			return ans;
		}
		ans[0] = root1;
		ans[1] = root2;
		return ans;
	}
	/**
     * square root of a number
     * @param a      {@code double} a double number to be calculated
	 * @return 		 answer in Double type
     */
	public Double getSquareRoot(double a){
		return Math.sqrt(a);
	}
}