package master.multiThead.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Describe:创建一个单线程化的线程池
 * Created by pengp on 2017/12/6.
 */
public class NewSingleThreadExecutor {
    public static void main(String[] args) {
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 100; i++) {
            final int index = i;
            singleThreadExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("线程"+index+"正在运行");
                }
            });
        }
        singleThreadExecutor.shutdown();
    }
}
