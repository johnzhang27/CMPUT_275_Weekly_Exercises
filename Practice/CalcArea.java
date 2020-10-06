public class CalcArea
{
 static double Pi = 3.14159;
 public static double rectangleArea(int width, int height) {
   double area = width * height;
   return area;
}
 public static double circleArea(int radius) {
   double area = Pi * radius * radius;
   return area;
}
 public static void main(String[] args) {
   int w = 5;
   int h = 6;
   double rectArea = rectangleArea(w, h);
   double circArea;
   if (w < h) {
     circArea = circleArea(w/2);
   } else {
     circArea = circleArea(h/2);
   }
   System.out.println("Rectangle area ("+w+"x"+h+") = " + rectArea);
   System.out.println("Area of circumscribed circle = " + circArea);
} }
 