/**
 * Assignment 5: Interfaces <br />
 * Part 2: The {@code inheritance} classes
 * @author Yongquan Zhang 1515873
 */

/*
U u;
G g;
B b;
Z z;
X x;
The following assignments are all legal and compile:

u = z; 
x = b; 
g = u; 
x = u;
However, the following assignments are all illegal and cause compilation errors:

u = b; 
x = g;
b = u; 
z = u; 
g = x;
*/
class U extends X implements G{

}
interface G{

}
class B extends X{

}
class Z extends U{

}
class X{

}
public class Exercise2 {
    public static void main(String[] args) {
		U u = new U();
		G g = new U();
		B b = new B();
		Z z = new Z();
		X x = new X();
		
		// legal
		u=z;
		x=b;
		g=u;
		x=u;
		
		// illegal
		u=b;
		x=g;
		b=u;
		z=u;
		g=x;
		
		System.out.println("Mission Completed");
	}
}
