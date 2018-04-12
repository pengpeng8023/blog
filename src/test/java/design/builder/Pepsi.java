package design.builder;

/**
 * Describe:
 * Created by pengp on 2018/4/10.
 */
public class Pepsi extends ColdDrink {

    @Override
    public float price() {
        return 35.0f;
    }

    @Override
    public String name() {
        return "Pepsi";
    }
}
