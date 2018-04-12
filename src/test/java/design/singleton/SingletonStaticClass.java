package design.singleton;

/**
 * Describe:
 * Created by pengp on 2018/4/10.
 */
public class SingletonStaticClass {
    private static class Singleton{
        private static final SingletonStaticClass INSTANCE = new SingletonStaticClass();
    }
    public static final SingletonStaticClass newInstnace(){
        return Singleton.INSTANCE;
    }
}
