package ece325;
/**
 * Assignment 5: Interfaces <br />
 * Part 3: The {@code PersonComparator]} class
 * @author Yongquan Zhang 1515873
 */
import java.util.Comparator;

public class PersonComparator implements Comparator<Person>{
    public PersonComparator(){}
    /**
     * Override the compare function of interface Comparator.
     * @param p1     {@code Person} person 1
     * @param p2     {@code Person} person 2
	 * @return 		 answer in int type (-1,0,1)
     */
    @Override
    public int compare(Person p1, Person p2){
        return Integer.compare(p1.getAge(), p2.getAge());
    }
}
