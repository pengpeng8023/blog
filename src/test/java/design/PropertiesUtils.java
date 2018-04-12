package design;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * Describe:
 * Created by pengp on 2018/4/10.
 */
public class PropertiesUtils {
    private String fileName ;
    //禁止指定重排 一步步走
    private volatile static PropertiesUtils propertiesUtils;
    private PropertiesUtils(String fileName){
        this.fileName = fileName;
    }
    private PropertiesUtils(){
    }
    public static PropertiesUtils newInstance(String fileName) {
        if (propertiesUtils == null){
            synchronized(PropertiesUtils.class){
                if (propertiesUtils == null){
                    propertiesUtils = new PropertiesUtils(fileName);
                }
            }

        }
        return propertiesUtils;
    }
    public static PropertiesUtils newInstance(){
        if (propertiesUtils == null){
            synchronized(PropertiesUtils.class){
                if (propertiesUtils == null){
                    propertiesUtils = new PropertiesUtils();
                    propertiesUtils.fileName = PropertiesUtils.class.getResource("/").getPath();
                    propertiesUtils.fileName = propertiesUtils.fileName.substring(0,propertiesUtils.fileName.substring(0,propertiesUtils.fileName.length()-1).lastIndexOf("/"))+"/classes/test.properties";
                }
            }

        }
        return propertiesUtils;
    }
    public static String getProperty(String key) {
        Properties properties = new Properties();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(propertiesUtils.fileName));
            properties.load(br);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty(key);
    }
    public static Object getClassInstance(String key) {
        try {
           return Class.forName(PropertiesUtils.newInstance().getProperty(key.toUpperCase())).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
