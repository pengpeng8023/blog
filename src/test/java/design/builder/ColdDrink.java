package design.builder;

/**
 * Describe:
 * Created by pengp on 2018/4/10.
 */
public abstract class ColdDrink implements Item {

    @Override
    public Packing packing() {
        return new Bottle();
    }

    @Override
    public abstract float price();
}
