import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * ECE 325 - Fall 2020
 * Assignment 4 Part 1: Static Code Analysis <br />
 * The buggy {@code CodingHorror} source code
 * @author YongQuan Zhang 1515873
 */
public class CodingHorror {
    /**
     * Main entry
     * @param args Command line arguments
     */
    public static void main(String args[]) {
        // TODO: Assignment 4 Part 1 -- run FindBugs on the code

        // Bug ignored(Internationalization): Found reliance on default encoding: new java.io.InputStreamReader(InputStream).

        // Reason: Found a call to a method which will perform a byte to String (or String to byte) conversion, 
        // and will assume that the default platform encoding is suitable. 
        // This will cause the application behaviour to vary between platforms. 
        // Use an alternative API and specify a charset name or Charset object explicitly.
        // we need to specify the type of character encoding (UTF8 or UTF16) in the file as well.

        // Solution: add an UTF8 encode (I don't know I found this solution on stack overflow but not working) 
        // InputStreamReader isr = new InputStreamReader(System.in, Charset.forName("UTF8"));
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String input = null;
        try {
            // Bug 1: Dereference of the result of readLine() without nullcheck

            // Reason: the input string could be null, we need to deal with this exception. 
            // The result of invoking readLine() is dereferenced without checking to see if the result is null. 
            // If there are no more lines of text to read, readLine() will return null 
            // and dereferencing that will generate a null pointer exception.

            // The solution is adding an "if statement" to check if the input is null.
            input = br.readLine();                  // e.g., peel 
            if(input == null || input.isEmpty()){
                throw new IOException("input string is null or empty.");
            }
        } catch (IOException ioex) {
            // Bug 2: Possible null pointer dereference in method on exception path

            // Reason: A reference value which is null on some exception control path is dereferenced here.
            // This may lead to a NullPointerException when the code is executed.

            // The solution is check if exception message is null.
            if(ioex.getMessage() == null){
                System.err.println("No IOException message.");
            }
            else{
                System.err.println(ioex.getMessage());
            }
            // Bug 3: Possible null pointer dereference

            // Reason: There is a branch of statement that, if executed, 
            // guarantees that a null value will be dereferenced, 
            // which would generate a NullPointerException when the code is executed.
            // This just means if we got a null or empty input then we should stop right here
            // instead of keeping going and do the replace opearion.

            // Solution: Terminate the function after print out error message.
            return;
        }


        // Bug 4: Method ignores return value. Return value of String.replace(char, char) ignored

        // Reason: String is immutable, so the replace method will return a new String value
        // But it is ignored here. 
        
        // The solution will be: set input = input.replace().
        input = input.replace('e', 'o');


        // Bug 5: Checking String equality using "==".

        // Reason: "==" is checking if the two string point to the same memory location but in our case
        // we want to compare the values of two string so equals() is what we should use. 

        // Solution: change "==" to equals("pool").
        if (input.equals("pool")) {
            System.out.println("User entered peel.");
        } else {
            System.out.println("User entered something else.");
        }
    }
}
