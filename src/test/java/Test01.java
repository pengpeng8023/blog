import org.junit.Test;
import redis.clients.jedis.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Describe:
 * Created by pengp on 2018/1/22.
 */
public class Test01 {
    @Test
    public void jedis(){
        Jedis jedis = new Jedis("192.168.177.11", 7000);
        //jedis.auth("123456");
        jedis.set("name", "tony");	//调用redis命令set
        String s = jedis.get("name");
        System.out.println(s);
        jedis.close();
    }
    @Test	//分片
    public void shard(){
        //构造各个节点链接信息，host和port
        List<JedisShardInfo> infoList = new ArrayList<JedisShardInfo>();
        JedisShardInfo info1 = new JedisShardInfo("192.168.177.11",7001);
        //info1.setPassword("123456");
        infoList.add(info1);
        JedisShardInfo info2 = new JedisShardInfo("192.168.177.11",7000);
        infoList.add(info2);
        JedisShardInfo info3 = new JedisShardInfo("192.168.177.11",7002);
        infoList.add(info3);

        //分片jedis
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(500);	//最大链接数
        ShardedJedisPool pool = new ShardedJedisPool(config, infoList);
        //ShardedJedis jedis = new ShardedJedis(infoList);
        ShardedJedis jedis = pool.getResource();	//从pool中获取
        Map m = new HashMap();
        m.put("a","a");
        m.put("b","b");
        jedis.hmset("ab",m);

        for(int i=0;i<10;i++){
            jedis.set("n"+i, "t"+i);
        }
        System.out.println(jedis.get("n6"));
        System.out.println(jedis.hmget("ab","a","b"));
        jedis.close();
    }
    @Test
    public void TestCalendar() throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        Date date =dateFormat.parse("20180226");
        calendar.setTime(date);
        int last = calendar.getActualMaximum(Calendar.DATE);
        System.out.println("该月的最后一天"+last);
    }
    @Test
    public void operation(){
        System.out.println(hash(20));
        System.out.println(-1>>> 20);
        System.out.println(20>>> 12);
        System.out.println(20 >>> 7);
        System.out.println(20>>> 4);


    }
    int hash(int h) {
        // This function ensures that hashCodes that differ only by
        // constant multiples at each bit position have a bounded
        // number of collisions (approximately 8 at default load factor).
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }
}
