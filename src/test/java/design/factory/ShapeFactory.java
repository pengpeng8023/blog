package design.factory;

import design.PropertiesUtils;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.Properties;

/**
 * Describe:
 * Created by pengp on 2018/4/10.
 */
public class ShapeFactory {
    public Shape getShape(String shapeType) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        if (StringUtils.isEmpty(shapeType)) return null;
        return  (Shape) PropertiesUtils.getClassInstance(shapeType);
    }
}
