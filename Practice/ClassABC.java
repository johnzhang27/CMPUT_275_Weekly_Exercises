interface ServiceB{
    public void foo();
}
class ClassA{
    public void bar(){
        System.out.println("barA");
    }
}
class ClassC extends ClassA{
    public void foo(){
        System.out.println("fooC");
    }
}
class ClassD extends ClassA implements ServiceB{
    public void foo(){
        System.out.println("fooD");
    }
}
class ClassF implements ServiceB{
    public void foo(){
        System.out.println("fooF");
    }
    public void bar(){
        System.out.println("barF");
    }
}
class ClassE extends ClassD{
}
public class ClassABC {
    public static void main(String[] args){
        //ClassE y = new ClassD();
        //ServiceB w = new ClassF();
        //w.bar();
        //ServiceB b = new ClassE();
        //b.foo();
        //ClassA a = new ClassE();
        ServiceB x = new ClassC();
    }
    
}
