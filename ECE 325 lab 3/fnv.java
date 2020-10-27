/**
 * Lab 3: Inheritance, Interfaces, Hash and Big Number <br />
 * The {@code fnv} class
 * @author YongQuan Zhang 1515873
 */
import java.math.BigInteger;

public class fnv {
    /**
     * The fnv hash algorithm
     * @param input   {@code String} name of the customer
     * @return h      {@code BigInteger} a very large number
     */
    public static BigInteger fnv_hash(String input){
        BigInteger h = new BigInteger("14695981039346656037");
        BigInteger temp = new BigInteger("1099511628211");
        char[] inputArray = input.toCharArray();
        // Fowler–Noll–Vo hash function, the advantage is fast and easily to avoid conflicts
        for(int i = 0;i<input.length();i++){
            h = (h.multiply(temp)).xor(BigInteger.valueOf(inputArray[i]));
            // mode 2^64 to prevent overflow
            h = h.mod(new BigInteger("2").pow(64));
        }
        return h;
    }
}
