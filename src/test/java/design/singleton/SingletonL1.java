package design.singleton;

/**
 * Describe:懒汉式 线程不安全
 * Created by pengp on 2018/4/10.
 */
public class SingletonL1 {
    private static SingletonL1 instance;
    public static SingletonL1 newInstance(){
        if (instance == null){
            return new SingletonL1();
        }
        return instance;
    }
}
