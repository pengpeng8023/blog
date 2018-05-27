package design.abstractFactory;

import design.factory.Shape;

/**
 * Describe:
 * Created by pengp on 2018/4/10.
 */
public class Red implements Color {

    @Override
    public void fill() {
        System.out.println("Inside Red::fill() method.");
    }
}
