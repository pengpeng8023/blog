package design.builder;

/**
 * Describe:
 * Created by pengp on 2018/4/10.
 */
public class VegBurger extends Burger {

    @Override
    public float price() {
        return 25.0f;
    }

    @Override
    public String name() {
        return "Veg Burger";
    }
}
