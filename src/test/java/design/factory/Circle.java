package design.factory;

/**
 * Describe:
 * Created by pengp on 2018/4/10.
 */
public class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Inside Circle::draw() method.");
    }
}
