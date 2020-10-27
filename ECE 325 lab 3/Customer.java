/**
 * Lab 3: Inheritance, Interfaces, Hash and Big Number <br />
 * The {@code Customer} class
 * @author YongQuan Zhang 1515873
 */
public class Customer extends Person implements Printable {
    private double projPrice;
    /**
     * The constructor
     * @param name   {@code String} name of the customer
     * @param projPrice  {@code double} the price of the project
     */
    public Customer(String name, double projPrice){
        super(name);
        this.projPrice = projPrice;
    }
    /**
     * The required get project price function
     * @return this.projPrice {@code double} return the price of the project
     */
    public double getProjPrice(){
        return this.projPrice;
    }
    /**
     * The abstract function in Printable interface
     * @return {@code String} return a string of required information
     */
    public String PrintInfo() {
		return  "Name of customer: " + super.getName() + "\n" + 
				"Project price: " + this.getProjPrice() + "\n";
	}
}
