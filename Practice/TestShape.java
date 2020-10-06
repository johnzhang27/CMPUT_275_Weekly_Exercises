abstract class Shape {
    private String name;
    private String color;
  
    public Shape(String name, String color) {
      this.name = name;
      this.color = color;
    }
  
    public String getName() { return name; }
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
  
    public String toString() { return name+" ("+color+")"; }
      
    public abstract double area();
    public abstract double perimeter();
    public abstract double[] dimensions();
}

class Rectangle extends Shape
{
private double height;
private double width;

public Rectangle(String name, double h, double w)
{
    super(name, "Blue");
    height = h;
    width = w;
}

public double area() { return height*width; }

public double perimeter() { return 2*height + 2*width; }

public double[] dimensions() { 
    double[] dim = { height, width };
    return dim; 
}
}

class Square extends Rectangle
{
public Square(String name, double side)
{
    super(name, side, side);
    setColor("Purple");
}
}
public class TestShape {
    public static void main(String[] args){
    Shape fig1 = new Rectangle("Rectangle", 10, 13);
    Shape fig2 = new Square("Square", 8);

    System.out.println("fig1.class: "+fig1.getClass());
    System.out.println("fig2.class: "+fig2.getClass());

    //Rectangle rect = fig2; //Shape cannot be converted to Rectangle

    Rectangle rect = (Square)fig2;
    System.out.println("rect: "+rect);
    }
}
