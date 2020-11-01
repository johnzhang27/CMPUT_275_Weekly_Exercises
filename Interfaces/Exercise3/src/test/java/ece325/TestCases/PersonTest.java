/**
 * Assignment 5: Interfaces <br />
 * Part 3: The {@code PersonTest} class
 * @author Yongquan Zhang 1515873
 */
package ece325.TestCases;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.Set;
import java.util.TreeSet;
import java.util.*;

import ece325.*;

public class PersonTest {
    private Set<Person> persons;

    @Before
    public void setUp() throws Exception {
        persons = new TreeSet<Person>(new PersonComparator());
        persons.add(new Person(32));
        persons.add(new Person(17));
        persons.add(new Person(13));
        persons.add(new Person(35));
        persons.add(new Person(27));
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * JUnit test section, the goal is to determine whether the compare function
     * in PersonComparator.java is valid or not. The persons set should be sorted
     * by age from smallest to largest.
     */
    @Test
    public void runTest() {
        // TODO: Assignment 5 Part 3 -- rewrite this using JUnit
        int lastAge = 0;
        for (Person ss : persons) {
            assertTrue(lastAge <= ss.getAge());
            lastAge = ss.getAge();
        }
    }
}
