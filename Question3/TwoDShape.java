/**
 * Assignment 7: Type Compatibility and Generics <br />
 * The {@code TwoDShape} interface
 * @author Yongquan Zhang 1515873
 */
public interface TwoDShape<T extends TwoDShape<T>> extends GeometricShape<T> {
    public double area();
    public T supersize();
}
