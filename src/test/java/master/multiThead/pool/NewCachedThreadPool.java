package master.multiThead.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Describe:创建一个可缓存线程池,可增可减
 * Created by pengp on 2017/12/6.
 */
public class NewCachedThreadPool {
    public static void main(String[] args) {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            final int index = i;
            try {
                Thread.sleep(index * 100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            cachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("线程"+index+"正在运行");
                }
            });
        }
        cachedThreadPool.shutdown();
    }
}
