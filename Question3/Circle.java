/**
 * Assignment 7: Type Compatibility and Generics <br />
 * The {@code Circle} class
 * @author Yongquan Zhang 1515873
 */
public class Circle implements TwoDShape<Circle> {

    private double radius;

    public Circle (double radius) {
        this.radius = radius;
    }

    public double area() {
        return Math.PI * radius * radius;
    }

    public double circumference() {
        return Math.PI * 2.0 * radius;
    }

    public void describe() {
        System.out.println("Circle[radius=" + radius + "]");
    }
    public Circle supersize(){
        return new Circle(2.0 * radius);
    }

}
