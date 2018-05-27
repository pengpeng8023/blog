package design.singleton;

/**
 * Describe:
 * Created by pengp on 2018/4/10.
 */
/**
 * Describe:懒汉式 线程安全
 * Created by pengp on 2018/4/10.
 */
public class SingletonL2 {
    private static SingletonL2 instance;
    public static synchronized SingletonL2 newInstance(){
        if (instance == null){
            return new SingletonL2();
        }
        return instance;
    }
}
