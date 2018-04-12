package design.singleton;

/**
 * Describe:
 * Created by pengp on 2018/4/10.
 */
public class SingletonE1 {
    private static SingletonE1 instance = new SingletonE1();
    public static SingletonE1 newInstance(){
        return instance;
    }
}
