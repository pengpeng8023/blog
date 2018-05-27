package staticTest;

/**
 * Describe: 父类静态代码块->子类静态代码块->父类代码块->父类构造方法->子类代码块->子类构造方法
 * Created by pengp on 2018/4/10.
 */
public class ExtendA {
    static {
        System.out.println("父类静态代码块");
    }
    {
        System.out.println("父类代码块");
    }
    public ExtendA(){
        System.out.println("父类构造方法");
    }

    public static void main(String[] args) {
        new ExtendB();
    }
}
class ExtendB extends ExtendA {
    static {
        System.out.println("子类静态代码块");
    }
    {
        System.out.println("子类代码块");
    }
    public ExtendB(){
        System.out.println("子类构造方法");
    }
}
