package design.builder;

/**
 * Describe:
 * Created by pengp on 2018/4/10.
 */
public class Coke extends ColdDrink {

    @Override
    public float price() {
        return 30.0f;
    }

    @Override
    public String name() {
        return "Coke";
    }
}
