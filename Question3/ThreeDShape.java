/**
 * Assignment 7: Type Compatibility and Generics <br />
 * The {@code ThreeDShape} interface
 * @author Yongquan Zhang 1515873
 */
public interface ThreeDShape<T extends ThreeDShape<T>> extends GeometricShape<T> {
    public double volume();
    public T supersize();
}
