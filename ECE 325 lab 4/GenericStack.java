import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.Vector;

/**
 * Lab 4: Generics <br />
 * The {@code GenericStack} class
 * @author Yongquan Zhang 1515873
 */

public class GenericStack<T> extends Vector<T>{
    // Constructor
    public GenericStack(){
        super(0,1);
    }
    /**
     * Query the top element
     * @return          {@code T} the top element
     */
    public T peek() {
        // TODO: Lab 4 Part 1-1 -- GenericStack, finish the peek method
        if(this.isEmpty()){
            return null;
        }
        return this.get(super.size()-1);
    }

    /**
     * Add a new element as top element
     * @param value     {@code T} the new element
     */
    public void push(T value) {
        // TODO: Lab 4 Part 1-2 -- GenericStack, finish the push method
        this.add(value);
    }

    /**
     * Remove the top element
     * @return          {@code T} the removed element
     */
    public T pop() {
        // TODO: Lab 4 Part 1-3 -- GenericStack, finish the pop method
        if(this.isEmpty()){
            return null;
        }
        return this.remove(this.size()-1);
    }

    /**
     * Query the size of the stack
     * @return          {@code int} size of the element
     */
    @Override
    public int size() {
        // TODO: Lab 4 Part 1-4 -- GenericStack, finish the size method
        if(this.isEmpty()){
            return 0;
        }
        return super.size();
    }

    /**
     * Check if the stack is empty of not
     * @return          {@code boolean} {@code true} for empty; {@code false} for not
     */
    @Override
    public boolean isEmpty() {
        // TODO: Lab 4 Part 1-5 -- GenericStack, finish the isEmpty method
        return super.isEmpty();
    }

    /**
     * Calculate a postfix expression
     * @param exp       {@code String} the postfix expression
     * @return          {@code Double} the value of the expression
     */
    public static Double calcPostfixExpression(String exp) {
        // TODO: Lab 4 Part 1-6 -- GenericStack, calculate postfix expression
        GenericStack<Double> stack = new GenericStack<>();
        Double num1,num2;
        List<String> seperatedStrings = new ArrayList<String>();
        seperatedStrings = Arrays.asList(exp.split(" "));
        List<String> operations = new ArrayList<String>();
        String[] ops = {"+","-","*","/","^"};
        operations = Arrays.asList(ops);

        for(String ss: seperatedStrings){
            // Push numbers into stack
            if(!operations.contains(ss)){
                stack.push(Double.parseDouble(ss));
            }
            // Check all operations
            else{
                num2 = stack.pop();
                num1 = stack.pop();

                if(ss.equals("+")){
                    stack.push(num1+num2);
                }
                else if(ss.equals("-")){
                    stack.push(num1-num2);
                }
                else if(ss.equals("*")){
                    stack.push(num1*num2);
                }
                else if(ss.equals("/")){
                    stack.push(num1/num2);
                }
                else if(ss.equals("^")){
                    stack.push(Math.pow(num1, num2));
                }
            }
        }

        return stack.pop();
    }

    /**
     * Main entry
     * @param args      {@code String[]} Command line arguments
     */
    public static void main(String[] args) {
        String[] expressions = {
                "4 1 +",                    // 1: = 5
                "2 6 -",                    // 2: = -4
                "3 3 *",                    // 3: = 9
                "1 4 /",                    // 4: = 0.25
                "2 3 ^",                    // 5: = 8
                "2 1 + 4 * 8 - 3 ^ 6 -",    // 6: 58
        }; // String[] expressions = { ... };
        for (String s: expressions)
            System.out.println(s + " = " + calcPostfixExpression(s));
    }

}
