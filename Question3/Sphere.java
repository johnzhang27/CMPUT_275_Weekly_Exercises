/**
 * Assignment 7: Type Compatibility and Generics <br />
 * The {@code Sphere} class
 * @author Yongquan Zhang 1515873
 */
public class Sphere implements ThreeDShape<Sphere>  {

    private double radius;

    public Sphere (double radius) {
        this.radius = radius;
    }

    public double volume() {
        return (4.0 / 3.0) * Math.PI * radius * radius * radius;
    }

    public void describe() {
        System.out.println("Sphere[radius=" + radius + "]");
    }
    public Sphere supersize(){
        return new Sphere(2.0 * radius);
    }
}
