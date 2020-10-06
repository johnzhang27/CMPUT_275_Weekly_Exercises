interface OptionA{
    public void foo();
}
interface OptionB{
    public void bar();
}
class A implements OptionA{
    public void foo(){
        System.out.println("Foo from ClassA");
    }

}
class B extends A implements OptionB{
    public void foo(){
        System.out.println("Foo from ClassA\n"+"Foo from ClassB");
    }
    public void bar(){
        System.out.println("Bar from ClassB");
    }
}
public class TestFoo {
    public static void main(String[] args) {
        OptionA v = new A();
        OptionA x = new B();
        OptionB y = new B();
        A z = new B();

        v.foo();
        x.foo();
        y.bar();
        z.foo();
        y.foo();// cause a error since the interface OptionB only know bar() method.
    }

    
}
