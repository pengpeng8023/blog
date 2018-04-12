package design.abstractFactory;

import design.PropertiesUtils;
import design.factory.Circle;
import design.factory.Rectangle;
import design.factory.Shape;
import design.factory.Square;

/**
 * Describe:
 * Created by pengp on 2018/4/10.
 */
public class ShapeFactory extends AbstractFactory {

    @Override
    public Shape getShape(String shapeType) {
        if(shapeType == null) return null;
        return (Shape) PropertiesUtils.getClassInstance(shapeType);
    }

    @Override
    Color getColor(String color) {
        return null;
    }
}
