class Counter
{
  private long counter;

  Counter() {
    counter =0;
  }

  // Write your method(s) here
  public void operation(String operations){
        System.out.println("Number of elements = "+counter);
  }
  public void operation(String operations, double num){
        System.out.println("Only support integer values");
}
  public void operation(String operations, int num){
        if (num < 0){
            System.out.println("Only support positive integer values");
        }
        else{
            if(operations.equals("add")){
                counter += num;
                System.out.println(num+" Elements added");
            }
            else if(operations.equals("remove")){
                if(counter < num){
                    System.out.println("Not enough elements");
                }
                else{
                    counter -= num;
                    System.out.println(num+" Elements removed");
                }
            }
            else{
                System.out.println("Wrong operation");
            }
        }
  }

} 

public class Question
{
  public static void main(String[] args) {
    Counter c = new Counter();
    c.operation("add", 10);     // print: "10 elements added"
    c.operation("remove", 5);   // print: "5 elements removed"
    c.operation("add", -2);     // print: "Only support positive integer values"
    c.operation("add", 3.7);    // print: "Only support integer values"
    c.operation("plus", 13);    // print: "Wrong operation"
    c.operation("remove", 10);  // print: "Not enough elements"
    c.operation("report");      // print: "Number of elements = 5"
  }
}