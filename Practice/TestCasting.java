class Employee1
{
    private static int sequence = 0;
    private final int id;
    private final String name;
    protected double salary;
    
    public Employee1(String name, double salary) {
        this.id = ++sequence;
        this.name = name;
        this.salary = salary;
    }
    
    public String getName() { return name; }

    public double getSalary() { return salary; }
    
    public void setSalary(double newSalary) { this.salary = newSalary; }    
}

class Manager1 extends Employee1
{
    private double bonus;
    
    public Manager1(String name, double salary, double bonus) {
        super(name, salary);
        this.bonus = bonus;
    }
    
    public double getSalary() {
        return super.getSalary() + bonus;
    }
    
    public void setBonus(double bonus) { this.bonus = bonus; }
}

public class TestCasting{
    public static void main(String[] args){
    Manager1 mng = new Manager1("John Smith", 50000, 2000);

    System.out.println(mng.getName() +" "+mng.getSalary());

    Manager1 emp = mng;

    System.out.println(emp.getName() +" "+emp.getSalary());

    Manager1 mng2 = (Manager1) emp;

    System.out.println(mng2.getName() +" "+mng2.getSalary());
    }

    Employee1 emp = new Employee1("Scott Tiger", 30000);

    //System.out.println(emp.getName() +" " +emp.getSalary());
    
    Manager1 mng = emp;
    
    Manager1 mng = (Manager1) emp;
    
    System.out.println(mng.getName() +" "+mng.getSalary());
}