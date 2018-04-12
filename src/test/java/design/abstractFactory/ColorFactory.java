package design.abstractFactory;

import design.PropertiesUtils;
import design.factory.Shape;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Describe:
 * Created by pengp on 2018/4/10.
 */
public class ColorFactory extends AbstractFactory {

    @Override
    public Shape getShape(String shapeType){
        return null;
    }

    @Override
    Color getColor(String color) {
        if(color == null) return null;
        return (Color) PropertiesUtils.getClassInstance(color);
    }
}
