package master.multiThead.download;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by pengp on 2017/12/5.
 */
public class Demo {
    public static Logger log = Logger.getLogger(Demo.class);
    public static String path = "http://softdownload.hao123.com/hao123-soft-online-bcs/soft/Y/2013-07-18_YoudaoDict_baidu.alading.exe";
    public static int threadCount = 5;
    public static class DownLoadThread extends Thread{
        private int threadId;
        private int startIndex;
        private int endIndex;
        private String path;
        private Long startTimes;

        public DownLoadThread(String path,int threadId,int startIndex, int endIndex,Long startTimes){
            super();
            this.path = path;
            this.threadId = threadId;
            this.startIndex = startIndex;
            this.endIndex = endIndex;
            this.startTimes = startTimes;
        }
        @Override
        public void run() {
            try {
                log.debug("线程:"+threadId+"开始下载:---"+startIndex+"--->"+endIndex);
                URL url = new URL(path);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setConnectTimeout(5000);
                conn.setRequestMethod("GET");
                //重要:请求服务器下载部分文件,指定文件的位置
                conn.setRequestProperty("Range","byte="+startIndex+"-"+endIndex);
                //获取服务器请求全部资源返回的响应码
                int code = conn.getResponseCode();
                if (code == 206){
                    InputStream is = conn.getInputStream();//返回当前对应位置的文件流
                    RandomAccessFile raf = new RandomAccessFile("setup.exe","rwd");
                    raf.seek(startIndex);//定位指针

                    int len;
                    byte[] buffer = new byte[1024];
                    while ((len = is.read(buffer)) != -1){
                        raf.write(buffer,0,len);
                    }
                    is.close();
                    raf.close();
                    log.debug("线程:"+threadId+"下载完毕!");
                }else {
                    log.debug("响应码是" +conn.getResponseCode() + ". 服务器不支持多线程下载");
                }
                log.debug("线程:"+threadId+"结束用时:" + (System.currentTimeMillis()-startTimes));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
//    @Test
    public static void main(String[] args) throws IOException {
        Long startTimes = System.currentTimeMillis();
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5000);
        conn.setRequestMethod("GET");
        int code = conn.getResponseCode();
        if (code == 200) {
            int length = conn.getContentLength();
            log.debug("文件总长度为:" + length);
            RandomAccessFile raf = new RandomAccessFile("setup.exe", "rwd");
            raf.setLength(length);
            raf.close();

            int blockSize = length / threadCount;
            for (int threadId = 1; threadId <= threadCount; threadId++) {
                int startIndex = (threadId - 1) * blockSize;
                int endIndex = threadId * blockSize - 1;
                if (threadId == threadCount) {
                    endIndex = length;
                }
              Thread t =  new DownLoadThread(path, threadId, startIndex, endIndex,startTimes);
                t.start();
            }
        }
    }
}
