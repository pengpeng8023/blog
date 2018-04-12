package design.abstractFactory;


/**
 * Describe:
 * Created by pengp on 2018/4/10.
 */
public class Green implements Color {

    @Override
    public void fill() {
        System.out.println("Inside Green::fill() method.");
    }
}
