package lab_two;
import java.util.Arrays;

/**
 * Lab 1: Java Basics, Merge Sort and Maven <br />
 * The {@code MergeSort} class
 * @author YongQuan Zhang 1515873
 */
public class MergeSort {

    /**
     * The merge procedure for merge sort
     * @param numbers   {@code int[]} The integer array to be sorted
     * @param leftHalf  {@code int[]} The left half of integer array to be merged
     * @param rightHalf {@code int[]} The right half of integer array to be merged
     */
    public static int[] merge(int[] leftHalf, int[] rightHalf, int[] numbers) {
        int leftSize = leftHalf.length;
        int rightSize = rightHalf.length;
        int i=0,j=0,k =0;
        while(i<leftSize&&j<rightSize){
            if(leftHalf[i]<=rightHalf[j]){
                numbers[k] = leftHalf[i];
                i++;
                k++;
            }
            else{
                numbers[k] = rightHalf[j];
                j++;
                k++; 
            }
        }
        while(i<leftSize){
            numbers[k] = leftHalf[i];
            i++;
            k++;
        }
        while(j<rightSize){
            numbers[k] = rightHalf[j];
            j++;
            k++; 
        }
        return numbers;
    }
    /**
     * The merge sort procedure
     * @param numbers   {@code int[]} The integer array to be sorted
     */
    public static int[] sort(int[] numbers) {
        // TODO: Lab 1 -- write mergesort here
        int size = numbers.length;
        if(size <= 1){
            return numbers;
        }
        int mid = (size)/2;
        int[] firstHalf = new int[mid];
        int[] secondHalf = new int[size-(size/2)];
        firstHalf = Arrays.copyOfRange(numbers, 0, mid);
        secondHalf = Arrays.copyOfRange(numbers, mid, size);
        sort(firstHalf);
        sort(secondHalf);
        numbers = merge(firstHalf, secondHalf, numbers);
	    return numbers;
    }

    /**
     * Main entry: test the HeapSort
     * @param args      {@code String[]} Command line arguments
     */
    public static void main(String[] args) {
        int[] numbers = new int[10];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = (int) (Math.random() * 200);
            System.out.print(numbers[i] + " ");
        }
        System.out.println();

        numbers = sort(numbers);

        for (int n: numbers)
            System.out.print(n + " ");
        System.out.println();
    }

}
