package master.multiThead.pool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Describe:创建一个定长,支持定时及周期性任务执行
 * Created by pengp on 2017/12/6.
 */
public class NewScheduledThreadPool {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
        //延迟
        //schedule(scheduledExecutorService);
        //scheduledExecutorService.shutdown();

        //定时
        scheduleAtFixedRate(scheduledExecutorService);
    }
    private static void schedule(ScheduledExecutorService scheduledExecutorService){
        scheduledExecutorService.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("delay 3 seconds");
            }
        },3, TimeUnit.SECONDS);
    }
    private static void scheduleAtFixedRate(ScheduledExecutorService scheduledExecutorService){
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("delay 1 seconds, and excute every 3 seconds");
            }
        },1,3,TimeUnit.SECONDS);
    }
}
