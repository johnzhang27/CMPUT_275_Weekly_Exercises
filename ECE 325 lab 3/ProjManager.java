/**
 * Lab 3: Inheritance, Interfaces, Hash and Big Number <br />
 * The {@code ProjManager} class
 * @author YongQuan Zhang 1515873
 */
import java.time.MonthDay;
import java.util.Date;

public class ProjManager extends SwEngineer implements SalaryRaisable, Printable{
    private Date projDeadline;
    // Constructor
    public ProjManager(String name, double baseSalary, String projName, Date projDeadline){
        super(name, baseSalary, projName);
        this.projDeadline = projDeadline;
    }

    public Date getProjDeadline(){
        return this.projDeadline;
    }

    public String PrintInfo() {
		return  "Name of Project Manager: " + super.getName() + "\n" + 
			    "Project name: " + super.getProjName() + "\n" +
			    "Final Salary: $" + this.RaiseSalary() + "\n" +
				"Project Deadline: " + this.getProjDeadline() + "\n";
    }

    public double RaiseSalary() {
        double currentSalary = super.getBaseSalary();
        return currentSalary*1.24;
    }
	/**
     * Override "equals" and "==" for Employee class
	 * Override hashcode is required
     */
    @Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		} else if (!(obj instanceof ProjManager)) {
			return false;
		}
		ProjManager projmanager = (ProjManager) obj;
        return (this.getName().equals(projmanager.getName()) &&
        this.getBaseSalary() == projmanager.getBaseSalary()) &&
        this.getProjName().equals(projmanager.getProjName()) &&
        this.getProjDeadline().equals(projmanager.getProjDeadline());
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.getName(), this.getBaseSalary(), this.getProjName(), this.getProjDeadline());
	}
}
