/**
 * Lab 3: Inheritance, Interfaces, Hash and Big Number <br />
 * The {@code SwEngineer} class
 * @author Yongquan Zhang 1515873
 */

public class SwEngineer extends Employee {
    private String projName;
    // Constructor
    public SwEngineer(String name, double baseSalary, String projName){
        super(name, baseSalary);
        this.projName = projName;
    }

    public String getProjName(){
        return this.projName;
    }

	/**
     * Override "equals" and "==" for Employee class
	 * Override hashcode is required
     */
    @Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		} else if (!(obj instanceof SwEngineer)) {
			return false;
		}
		SwEngineer swengineer = (SwEngineer) obj;
        return (this.getName().equals(swengineer.getName()) &&
        this.getBaseSalary() == swengineer.getBaseSalary()) &&
        this.getProjName().equals(swengineer.getProjName());
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.getName(), this.getBaseSalary(), this.getProjName());
	}
}
