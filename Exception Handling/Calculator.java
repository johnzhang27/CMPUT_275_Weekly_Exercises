/**
 * ECE 325 - Fall 2020 <br/>
 * Assignment 3: Exception handling <br />
 * Calculator using BNF
 * <p>
 * @author YongQuan Zhang 1515873
 */

import java.util.regex.*;
import java.util.Arrays;
import java.util.HashMap;

@SuppressWarnings("serial")
class SyntaxError extends Exception {
    /**
     * Constructor which create a new syntax error exception
     */
    public SyntaxError(String msg){
        super(msg);
    }
}
@SuppressWarnings("serial")
class RuntimeError extends Exception {
    /**
     * Constructor which create a new syntax error exception
     */
    public RuntimeError(String msg) {
        super(msg);
    }
}

public class Calculator {
    // all characters/ numbers/ operations before the first ")".
    private Pattern variable = Pattern.compile("let [a-z] = [^)]+");
    private int ans = -1;
    // (.......), One complete bracket
    private Pattern brackets = Pattern.compile("\\([^\\(\\)]*\\)");
    // 1^1 or 1^a or a^1 or a^a
    private Pattern power = Pattern.compile("([0-9]+|[a-z]) \\^ ([0-9]+|[a-z])");
    // 1*1 or 1*a or a*1 or a*a or division
    private Pattern mulOrDiv = Pattern.compile("([0-9]+|[a-z]) (\\*|/) ([0-9]+|[a-z])");
    // 1+1 or 1+a or a+1 or a+a or minus
    private Pattern addOrSub = Pattern.compile("([0-9]+|[a-z]) (\\+|-) ([0-9]+|[a-z])");


    /**
     * Break the String exp to small pieces and calculate each of them
     * This is a recursion function
     * @param exp              {@code String} The array of operations to be calculated
     * @param variableMap      {@code HashMap<Character, Integer>} A hashmap contains all varibale names and their values
     * @return                 {@code int} The value of the expression     
     * @throws SyntaxError
     * @throws RuntimeError     
     */
    private int findOperation(String exp, HashMap<Character, Integer> variableMap) throws RuntimeError, SyntaxError{
        HashMap<Character, Integer> temp;
        if(variableMap == null){
            temp =new HashMap<>();
        }
        else{
            temp = variableMap;
        }
        Matcher matcher;
        // Nesessary, otherwise there will be a hashmap null error
        String input = exp.replace(";", "");
        // This order is important, we need to make sure the operations in bracket be calculated first
        // Also, do power before multiplication/division, do m/d before addition/substraction
        if(brackets.matcher(input).find()){
            matcher = brackets.matcher(input);
        } 
        else if(power.matcher(input).find()){
            matcher = power.matcher(input);
        }
        else if(mulOrDiv.matcher(input).find()){
            matcher = mulOrDiv.matcher(input);
        }
        else if(addOrSub.matcher(input).find()){
            matcher = addOrSub.matcher(input);
        }
        else if(variable.matcher(input).find()){
            matcher = variable.matcher(input);
        }
        else{      
            // No operation, return the initial result.  	       	
            try {
                Pattern p = Pattern.compile("[a-z0-9] [a-z0-9]");
                Matcher exceptionMatcher = p.matcher(input);
                if(exceptionMatcher.find()){
                    throw new SyntaxError("operator expected");
                }
                return Integer.valueOf(input);
            }
            catch(NumberFormatException e) {
                return variableMap.get(input.charAt(0));
            }
        }
       if(matcher.find()){    	   
    		   calculate(input, matcher.start(), matcher.end(), temp);         
        }
       return this.ans; 
    }


    /**
     * Calculate the small piece of string passed from findOperation function
     * This is a recursion function
     * @param input    {@code String} A small piece of original expression string 
     * @param head     {@code int} index of the start of the substring of exp
     * @param input    {@code int} index of the end of the substring of exp
     * @param variableMap      {@code HashMap<Character, Integer>} A hashmap contains all varibale names and their values
     * @throws SyntaxError
     * @throws RuntimeError     
     */
    private void calculate(String input, int head, int tail, HashMap<Character,Integer> variableMap) throws RuntimeError, SyntaxError {
        int ans;
        String exp = input.substring(head,tail);
        exp = exp.replaceAll("[\\(\\)]","");
        Matcher m = variable.matcher(exp);

        if(m.find()){
            Character varName = m.group().charAt(4);
            // This will return an integer which is the value of the variable at m.group().charAt(4)
            int varValue = findOperation(m.group().substring(8), variableMap);
            variableMap.put(varName, varValue);
            ans = varValue; 
        }
        // Math operations processing
        else{
            String[] operationArray = exp.split(" ");
            // If the operation is assigning variable to a value but "let" is not used
            if(Arrays.asList(operationArray).contains("=")){
                int index = Arrays.asList(operationArray).indexOf("=");
                throw new RuntimeError(String.format("'%s' undefined", operationArray[index-2]));
            }
            int left; int right;
            try{
                left = Integer.valueOf(operationArray[0]);
            }
            // If the thing on the left side of the operation is a charater then
            // go to the hashmap to find its value
            catch(NumberFormatException e){
                left = variableMap.get(operationArray[0].charAt(0));
            }
            try{
                right = Integer.valueOf(operationArray[2]);
            }
            // Do same thing with the right side
            catch(NumberFormatException e){
                try{
                    right = variableMap.get(operationArray[2].charAt(0));
                }
                // Right side variable should be assigned to some value but if not, throw an error
                catch(NullPointerException ee){
                    throw new RuntimeError(String.format("'%s' is undefined", operationArray[2]));
                }  
            }
            ans = operationCalc(operationArray[1], left, right);
        }
        this.ans = ans;
        String remainingString = input.substring(0, head)+Integer.toString(ans)+input.substring(tail);
        findOperation(remainingString,variableMap);
    }


    /**
     * Math caculation happens here
     * @param operation    {@code String} A string only contains math operation
     * @param left         {@code int} integer number on the left side of the operaion
     * @param right        {@code int} integer number on the right side ofthe operation
     * @return             {@code int} The value of the expression
     * @throws RuntimeError
     */
    private int operationCalc(String operation, int left, int right) throws RuntimeError {
        int ans;
        if(operation.charAt(0)==('*')){
            ans = left*right;
        }
        else if(operation.charAt(0)==('/')){
            ans = left/right;
        }
        else if(operation.charAt(0)==('+')){
            ans = left+right;
        }
        else if(operation.charAt(0)==('-')){
            ans = left-right;
        }
        else if(operation.charAt(0)==('^')){
            ans = (int) Math.pow(left, right);
        }
        else{
            throw new RuntimeError("Operator expected");
        }
        return ans;
    }
    
    /**
     * The function deal with syntax error
     * @param input    {@code String} The expression string
     * @throws SyntaxError
     */    
    private void dealSyntaxError(String input) throws SyntaxError{
        // Missing left brackets
        String allBrackets = input.replaceAll("[^\\(\\)]", "");	
        while(allBrackets.contains("()")){
            allBrackets = allBrackets.replace("()", "");
        }
        if(allBrackets.length() > 0){
            if(allBrackets.charAt(0) == '('){
                throw new SyntaxError("')' expected");
            }
        }
        // Missing right bracket
        Pattern p = Pattern.compile(" let [a-z]");
        Matcher m = p.matcher(input);
        if(m.find()){
            throw new SyntaxError("')' expected");
        }
        // Misssing equal operation
        p = Pattern.compile("let [a-z] [a-z0-9]");
        m = p.matcher(input);
        if(m.find()){
            throw new SyntaxError("'=' expected");
        }
    }

    /**
     * Execute the expression, and return the correct value
     * 
     * @param exp {@code String} The expression string
     * @return {@code int} The value of the expression
     * @throws SyntaxError
     * @throws RuntimeError
     */
    public int execExpression(String exp) throws SyntaxError, RuntimeError {
        int returnValue = -1;

    	dealSyntaxError(exp);
    	returnValue = findOperation(exp,null);

        return returnValue;
    }

    /**
     * Main entry
     * 
     * @param args {@code String[]} Command line arguments
     * @throws RuntimeError
     * @throws SyntaxError
     */
    public static void main(String[] args) throws SyntaxError, RuntimeError {
        Calculator calc = new Calculator();
        // Part 1
        String[] inputs = {
            "let x = 1;",                                                                           // 1, returns 1
            "(let x = 1) + x;",                                                                     // 2, returns 2
            "(let a = 2) + 3 * a - 5;",                                                             // 3, returns 3
            "(let x = (let y = (let z = 1))) + x + y + z;",                                         // 4, returns 4
            "1 + (let x = 1) + (let y = 2) + (1 + x) * (1 + y) - (let x = y) - (let y = 1) - x;",   // 5, returns 5
            "1 + (let a = (let b = 1) + b) + a + 1;",                                               // 6, returns 6
            "(let a = (let a = (let a = (let a = 2) + a) + a) + a) - 9;",                           // 7, returns 7
            "(let x = 2) ^ (let y = 3);",                                                           // 8, returns 8
            "(let y = 3) ^ (let x = 2);"                                                            // 9, returns 9
        };
        for (int i = 0; i < inputs.length; i++)
            System.out.println(String.format("%d -- %-90s %d", i+1, inputs[i], calc.execExpression(inputs[i])));
        // Part 2
        inputs = new String[] {
                "1 + (2 * 3;",                  // 1, syntax error: ')' expected
                "(let x 5) + x;",               // 2, syntax error: '=' expected
                "(let x = 5) (let y = 6);",     // 3, syntax error: operator expected
                "(let x = 5 let y = 6);",       // 4, syntax error: ')' expected
                "(ler x = 5) ^ (let y = 6);",   // 5, runtime error: 'ler' undefined
                "(let x = 5) + y;"              // 6, runtime error: 'y' undefined
        };
        // TODO: Assignment 3 Part 2-2 -- catch and deal with your exceptions here
        for (int i = 0; i < inputs.length; i++)
            try{
                System.out.println(String.format("%d -- %-30s %d", i+1, inputs[i], calc.execExpression(inputs[i])));
            }
            catch(SyntaxError e) {
                System.out.println(e);
            }
            catch(RuntimeError e) {
                System.out.println(e);
            }
    }
}
