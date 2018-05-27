package master.multiThead.pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by pengp on 2017/12/6.
 */
public class TestThreadPool {
    public static void main(String[] args) {
        //corePoolSize核心池大小,maximimPoolSize线程池最大线程数,keepAliveTime线程没有执行任务时最大保留时间,TimeUnit时间单位,ArrayBlockingQueue线程池的排队策略
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5,
                15, 200, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(5));
        for (int i = 0; i < 20; i++) {
            MyTask myTask = new MyTask(i);
            executor.execute(myTask);
            System.out.println("线程池线程数目:"+executor.getPoolSize()+"" +
                    ",队列中等待执行的数目"+executor.getQueue().size()+
                    ",已执行完毕的任务数"+executor.getCompletedTaskCount());
        }
        executor.shutdown();
    }
}
