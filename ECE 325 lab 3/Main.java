/**
 * Lab 3: Inheritance, Interfaces, Hash and Big Number <br />
 * The {@code Main} class
 * @author YongQuan Zhang 1515873
 */
import java.util.*;
import java.util.Date;
// test class
public class Main {
    public static void main(String[] args){
        // Deliverable 1
        System.out.println("Deliverable 1 :");
        Date today = new Date();
        Person Wick = new Person("Wick");
        System.out.println(Wick.getName());

        HwEngineer John = new HwEngineer("John", 3000);
        System.out.println(John.getName());
        System.out.println(John.getBaseSalary());
        System.out.println(John.RaiseSalary());

        Customer Zhang = new Customer("Zhang", 100000);
        System.out.println(Zhang.getProjPrice());
        System.out.println(Zhang.PrintInfo());
        
		ProjManager JohnZ = new ProjManager("JohnZ", 6000, "343i", today);
		
        System.out.println(JohnZ.PrintInfo());

        // Deliverable 2
        System.out.println("Deliverable 2 :");
        SwEngineer sw1 = new SwEngineer("Paul", 1000, "Paul Project");
        SwEngineer sw2 = new SwEngineer("Paul", 1000, "Paul Project");
        System.out.println("sw1 equals sw2; "+sw1.equals(sw2));

        SwEngineer sw3 = new SwEngineer("Paul", 10000, "Paul Project");
        SwEngineer sw4 = new SwEngineer("Paul", 1000, "Paul Project");
        System.out.println("sw3 equals sw4; "+sw3.equals(sw4));

        ProjManager p1 = new ProjManager("Vik", 200, "Vik's project", today);
        ProjManager p2 = new ProjManager("Vik", 300, "Vik's project", today);
        System.out.println("p1 equals p2: "+p1.equals(p2)+"\n");

        // Deliverable 3
        System.out.println("Deliverable 3 :");
        System.out.println("big number of 'hey' is: ");
        System.out.println(fnv.fnv_hash("hey"));
        System.out.println("big number of 'im big number' is: ");
		System.out.println(fnv.fnv_hash("I'm big number"));
    }
}
