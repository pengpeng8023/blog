package design.abstractFactory;

import design.factory.Shape;

import java.io.FileNotFoundException;

/**
 * Describe:
 * Created by pengp on 2018/4/10.
 */
public abstract class AbstractFactory {
    abstract Color getColor(String color);
    abstract Shape getShape(String shape);
}
