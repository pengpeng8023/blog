package com.study.blog.tool;

import org.springframework.beans.BeansException;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Describe:
 * Created by pengp on 2018/2/6.
 */
public class PropertiesListenerConfig {
    public static Map<String, String> propertiesMap = new HashMap<>();
    private static void processProperties(Properties properties) throws BeansException{
        propertiesMap = new HashMap<>();
        for(Object key :properties.keySet()){
            String keyStr = key.toString();
            try {
                propertiesMap.put(keyStr,new String(properties.getProperty(keyStr).getBytes("ISO-8859-1"),"utf-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }
    public static void loadAllProperties(String propertyFileName){
        try {
            Properties properties = PropertiesLoaderUtils.loadAllProperties(propertyFileName);
            processProperties(properties);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static String getProperty(String name){
        return propertiesMap.get(name)==null?"":propertiesMap.get(name).toString();
    }
    public static Map<String,String> getAllProperty(){
        return propertiesMap;
    }
}
