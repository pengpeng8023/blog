package design.singleton;

/**
 * Describe:
 * Created by pengp on 2018/4/10.
 */
public class SingleDoubleLock {
    private volatile static SingleDoubleLock instance;
    public static SingleDoubleLock newInstance(){
        if (instance == null){
            synchronized (SingleDoubleLock.class){
                if (instance ==null){
                    return new SingleDoubleLock();
                }
            }
        }
        return instance;
    }
}
