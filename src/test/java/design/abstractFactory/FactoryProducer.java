package design.abstractFactory;

import design.PropertiesUtils;

/**
 * Describe:
 * Created by pengp on 2018/4/10.
 */
public class FactoryProducer {
    public static AbstractFactory getFactory(String choice){
        if(choice == null) return null;
        return (AbstractFactory) PropertiesUtils.getClassInstance(choice);
    }
}
