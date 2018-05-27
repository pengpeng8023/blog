package design.factory;

/**
 * Describe:
 * Created by pengp on 2018/4/10.
 */
public class Square implements Shape {
    @Override
    public void draw() {
        System.out.println("Inside Square::draw() method.");
    }
}
