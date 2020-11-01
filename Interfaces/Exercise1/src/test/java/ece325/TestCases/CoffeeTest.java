/**
 * Assignment 5: Interfaces <br />
 * Part 1: The {@code CoffeeTest} class
 * @author Yongquan Zhang 1515873
 */
package ece325.TestCases;
// JUnit test imports
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
// Other imports
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import ece325.*;

public class CoffeeTest {
    private static List<Coffee> coffees = new ArrayList<Coffee>();

    @Before
    public void setUp() throws Exception {
        coffees.add(new Coffee(10));
        coffees.add(new Coffee(2));
        coffees.add(new Coffee(10));
        coffees.add(new Coffee(20));
        coffees.add(new Coffee(5));
    }

    @After
    public void tearDown() throws Exception {
    }
    /**
     * JUnit test section, the goal is to determine whether the compare function
     * in Coffee.java is valid or not. The coffees list should be sorted
     * by strength from smallest to largest.
     */
    @Test
    public void testComparable() {
        // TODO: Assignment 5 Part 1 -- rewrite this using JUnit
        Collections.sort(coffees);
        int lastStrength = 0;

        for (Coffee type : coffees) {
            assertTrue(lastStrength <= type.getStrength());
            lastStrength = type.getStrength();
        }
        System.out.println("Coffees in order of strength:");
        for (Coffee type : coffees) {
            System.out.println(type);
        }
    }
}
