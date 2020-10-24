package ece325.TestCases;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ece325.*;

/**
 * JUnit test class for solving square roots
 */
public class SquareRootTests {

    // TODO: Assignment 4 Part 2 -- Checkpoint4
    private Calculator calc;
    @Before 
    public void setUp() throws Exception {
        calc = new Calculator();
    }

    @After 
    public void tearDown() throws Exception {
    }

    @Test 
    public void testRandomPositiveSquareRoot() {
        // You cannot use the Math.sqrt() function in the test!
        double a = (Math.random() - 0.5) * 200000000;
        while(a<=0){
            a = (Math.random() - 0.5) * 200000000;
        }
        Double ans = calc.getSquareRoot(a*a);
        assertEquals(ans, Double.valueOf(a));
    }

    @Test 
    public void testRandomNegitiveSquareRoot() {
        // The result should be a complex number i.e. Double.isNaN()
        double a = (Math.random() - 0.5) * 200000000;
        while(a>=0){
            a = (Math.random() - 0.5) * 200000000;
        }
        Double ans = calc.getSquareRoot(a);
        assertTrue(ans.isNaN());
        
    }

    @Test 
    public void testSquareRootofZero() {
        // You cannot use the Math.sqrt() function in the test!
        Double ans = calc.getSquareRoot(0);
        assertEquals(ans, Double.valueOf(0));
        
    }

    @Test 
    public void testSquareRootofOne() {
        // You cannot use the Math.sqrt() function in the test!
        Double ans = calc.getSquareRoot(1);
        assertEquals(ans, Double.valueOf(1));
    }

}
