interface HasSides
{
    int numberOfSides();
}
abstract class Shape1 {
    private String name;
    private String color;
  
    public Shape1(String name, String color) {
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
class Rectangle1 extends Shape1 implements HasSides
{
  private double height;
  private double width;

  public Rectangle1(String name, double h, double w)
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
  
  public int numberOfSides() { return 4; }
}
class Square1 extends Rectangle1
{
  public Square1(String name, double side)
  {
    super(name, side, side);
    setColor("Purple");
  }
}
public class TestSlides {
    public static void main(String[] args){
    Shape1 fig1 = new Rectangle1("Rectangle", 10, 13);
    Rectangle1 fig2 = new Square1("Square", 8);

    //System.out.println(fig1+" # of sides: "+ fig1.numberOfSides()); // cannot find symbol numberOfSides

    HasSides fig3 = fig2;
    System.out.println(fig3+" # of sides: "+ fig3.numberOfSides());
    }
    
}
