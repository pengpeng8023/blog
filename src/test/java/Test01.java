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
    public void DocToHtml() throws ParserConfigurationException, IOException, TransformerException {
        final String path = "E:\\";
        final String file = "wordTemplate.doc";
        InputStream input = new FileInputStream(path + file);
        HWPFDocument wordDocument = new HWPFDocument(input);
        WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(
                DocumentBuilderFactory.newInstance().newDocumentBuilder()
                        .newDocument());
        wordToHtmlConverter.setPicturesManager(new PicturesManager() {
            public String savePicture(byte[] content, PictureType pictureType,
                                      String suggestedName, float widthInches, float heightInches) {
                return suggestedName;
            }
        });
        wordToHtmlConverter.processDocument(wordDocument);
        List pics = wordDocument.getPicturesTable().getAllPictures();
        if (pics != null) {
            for (int i = 0; i < pics.size(); i++) {
                Picture pic = (Picture) pics.get(i);
                try {
                    pic.writeImageContent(new FileOutputStream(path
                            + pic.suggestFullFileName()));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        Document htmlDocument = wordToHtmlConverter.getDocument();
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        DOMSource domSource = new DOMSource(htmlDocument);
        StreamResult streamResult = new StreamResult(outStream);
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer serializer = tf.newTransformer();
        serializer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
        serializer.setOutputProperty(OutputKeys.INDENT, "yes");
        serializer.setOutputProperty(OutputKeys.METHOD, "html");
        serializer.transform(domSource, streamResult);
        outStream.close();
        String content = new String(outStream.toByteArray());
        FileUtils.writeStringToFile(new File(path, "wordTemplate.html"), content, "utf-8");
    }
}
