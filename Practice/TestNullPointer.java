class ExceptionExample2 {
    public String[] myList;
    public void problemCode(String item) { try {
          myList[0] = item;
        }
    catch (NullPointerException e) { System.out.println("Problem!"); myList = new String[10]; System.out.println("Now try again");
    } }
    }
    public class TestNullPointer {
      public static void main(String[] args) {
    ExceptionExample2 e = new ExceptionExample2();
    e.problemCode("demo"); }
    }