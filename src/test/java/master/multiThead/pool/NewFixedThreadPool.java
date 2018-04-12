package master.multiThead.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Describe:创建一个定长线程池,可控制最大并发数,超出的线程会在队列中等待
 * Created by pengp on 2017/12/6.
 */
public class NewFixedThreadPool {
    public static void main(String[] args) {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 20; i++) {
            final int index = i;
            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("线程"+index+"正在运行");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        fixedThreadPool.shutdown();;
    }
}
