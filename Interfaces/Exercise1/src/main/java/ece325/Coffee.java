/**
 * Assignment 5: Interfaces <br />
 * Part 1: The {@code Coffee} class
 * @author Yongquan Zhang 1515873
 */
package ece325;

public class Coffee implements Comparable<Coffee> {
    private int strength;       // The strength of the coffee
    // TODO: Assignment 5 Part 1 -- complete this class

    // Constructor
    public Coffee(int strength){
        this. strength = strength;
    }
    public int getStrength(){
        return this.strength;
    }
    /**
     * Override the compareTo function of interface Comparable.
     * @param input      {@code Coffee} a Coffee type object.
	 * @return 		     answer in int type(-1,0,1)
     */
    @Override
    public int compareTo(Coffee input){

        return Integer.compare(this.strength, input.getStrength());
    }
}
