import com.study.blog.SpringbootApplication;
import com.study.blog.loginAndRegister.service.TestRedisService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.CountDownLatch;

/**
 * Describe: junit单元测试springboot需要注入
 *              @RunWith(SpringJUnit4ClassRunner.class)
 *              @SpringBootTest(classes = SpringbootApplication.class)
 * Created by pengp on 2018/4/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringbootApplication.class)
public class TestRedis {
    @Autowired
    private TestRedisService testRedisService;
    private static Long time = System.currentTimeMillis();
    private static final CountDownLatch cdl = new CountDownLatch(5000);
    private static final int threadNum = 5000;
    private static final String ID = "1";
    @Before
    public void start(){
        System.out.println("开始测试----------------------");
    }
    @After
    public void end(){
        System.out.println("结束测试----------------------"+(System.currentTimeMillis()-time));
    }

    @Test
    public void TestRedisXueBen() throws InterruptedException {
        Thread[] threads = new Thread[threadNum];
        for (int i=0;i<threadNum;i++){
            Thread thread = new Thread(new QueryRequest());
            threads[i] = thread;
            thread.start();
            cdl.countDown();
        }

        for (Thread thread:threads){
            thread.join();
        }
    }

    private class QueryRequest implements Runnable{

        @Override
        public void run() {
            try {
                cdl.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            testRedisService.testMethod(ID);
        }
    }
}
