/**
 * Lab 3: Inheritance, Interfaces, Hash and Big Number <br />
 * The {@code HwEngineer} class
 * @author YongQuan Zhang 1515873
 */
public class HwEngineer extends Employee implements SalaryRaisable {
    // Constructor
    public HwEngineer(String name, double baseSalary){
        super(name, baseSalary);
    }
    /**
     * The required raise salary function for SalaryRaisable interface
     * @return               {@code double} raised salary
     */
    public double RaiseSalary(){
        double currentSalary = super.getBaseSalary();
        return currentSalary*1.18;
    }
}
