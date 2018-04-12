package design.builder;

/**
 * Describe:
 * Created by pengp on 2018/4/10.
 */
public class ChickenBurger extends Burger {

    @Override
    public float price() {
        return 50.5f;
    }

    @Override
    public String name() {
        return "Chicken Burger";
    }
}
