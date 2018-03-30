import org.apache.commons.io.FileUtils;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.PicturesManager;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.usermodel.Picture;
import org.apache.poi.hwpf.usermodel.PictureType;
import org.apache.poi.hwpf.usermodel.Range;
import org.junit.Test;
import org.w3c.dom.Document;
import redis.clients.jedis.*;

import javax.servlet.ServletOutputStream;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
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
    @Test
    public void test001() throws Exception {
        Test01.deletefile("E:\\app");
    }

    public static boolean deletefile(String delpath) throws Exception {
        try {

            File file = new File(delpath);
            // 当且仅当此抽象路径名表示的文件存在且 是一个目录时，返回 true
            if (!file.isDirectory()) {
                file.delete();
            } else if (file.isDirectory()) {
                String[] filelist = file.list();
                for (int i = 0; i < filelist.length; i++) {
                    File delfile = new File(delpath + "\\" + filelist[i]);
                    if (!delfile.isDirectory()) {
                        delfile.delete();
                    } else if (delfile.isDirectory()) {
                        deletefile(delpath + "\\" + filelist[i]);
                    }
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("deletefile() Exception:" + e.getMessage());
        }
        return true;
    }
    @Test
    public void testBinaryTree(){
        BinaryTree bt = new BinaryTree(52);
        bt.insert(580);
        bt.insert(12);
        bt.insert(50);
        bt.insert(58);
        bt.insert(9);
        bt.insert(888);
        bt.insert(248);
        bt.insert(32);
        bt.insert(666);
        bt.insert(455);
        bt.insert(777);
        bt.insert(999);
        bt.inOrderTraverse();
        bt.preOrderTraverse();
        bt.postOrderTraverse();
        System.out.println(bt.findKey(32));
        System.out.println(bt.findKey(81));
        System.out.println("最小值:" + bt.getMinValue());
 //     bt.delete(32);      //删除叶子节点
 //     bt.delete(50);      //删除只有一个左子节点的节点
//      bt.delete(248);      //删除只有一个右子节点的节点
 //    bt.delete(580);      //删除有两个子节点的节点，且后继节点为删除节点的右子节点的左后代
//      bt.delete(888);      //删除有两个子节点的节点，且后继节点为删除节点的右子节点
      bt.delete(52);       //删除有两个子节点的节点，且删除节点为根节点

        bt.inOrderTraverse();
    }
}
