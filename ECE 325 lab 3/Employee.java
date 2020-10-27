/**
 * Lab 3: Inheritance, Interfaces, Hash and Big Number <br />
 * The {@code Employee} class
 * @author YongQuan Zhang 1515873
 */
public class Employee extends Person {
    private double baseSalary;
    /**
     * The constructor
     * @param name   {@code String} name of the customer
     * @param baseSalary  {@code double} the base salary
     */
    public Employee(String name, double baseSalary){
        super(name);
        this.baseSalary = baseSalary;
    }
    /**
     * The required get base salary function
     * @return this.projPrice {@code double} return the base salary
     */
    public double getBaseSalary() {
        return this.baseSalary;
    }

	/**
     * Override "equals" and "==" for Employee class
	 * Override hashcode is required
     */
    @Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		} else if (!(obj instanceof Employee)) {
			return false;
		}
		Employee employee = (Employee) obj;
        return (this.getName().equals(employee.getName())&&
        this.getBaseSalary() == employee.getBaseSalary());
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.getName(), this.getBaseSalary());
	}
}
