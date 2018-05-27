package design.builder;

/**
 * Describe:
 * Created by pengp on 2018/4/10.
 */
public abstract class Burger implements Item {

    @Override
    public Packing packing() {
        return new Wrapper();
    }

    @Override
    public abstract float price();
}
