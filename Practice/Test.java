interface ServiceA{
    public void print();
}
class A implements ServiceA{
    public void print() {
        System.out.println("Class A");
    }
}
class B implements ServiceA{
    public void print() {
        System.out.println("Class B");
    }
}
class D implements ServiceA{
    private B b;
    private A a;

    public D(A a){
        this.a = a;
        this.b = new B();
    }
    public void print(){
        System.out.println("Class D");
        a.print();
        b.print();
    }
}
class C extends A{
    public void print(){
        System.out.println("Class C");
    }
}
public class Test {

    static void test(ServiceA obj) {
        System.out.println("----------------------");
        obj.print();
    }

    public static void main(String[] args) {   
        A a1 = new C();
        A a2 = new A();
        D d1 = new D(a1);
        D d2 = new D(a2);
        test(d1);
        test(d2);
    }
}