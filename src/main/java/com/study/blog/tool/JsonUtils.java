package com.study.blog.tool;

/**
 * Describe:
 * Created by pengp on 2018/1/18.
 */

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.beanutils.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class JsonUtils {
    private final static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    public static Gson getInstance(){
        return gson;
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("aaa");
        System.out.println(list2Json(list));
    }
    /***
     * List 转为 JSON
     * @param list
     * @return
     */
    public static <T> String list2Json(List<T> list) {
        if(null != list && list.size() > 0){
            String jsonObject = JSONObject.toJSONString(list);
            return jsonObject;
        }
        return "";
    }


    /***
     * JSON 转换为 List
     * @param jsonStr
     *         [{"age":12,"createTime":null,"id":"","name":"wxw","registerTime":null,"sex":1},{...}]
     * @param objectClass
     * @return
     */

//    @SuppressWarnings("unchecked")
public static <T> List<T> json2List(String jsonStr, Class<T> objectClass){
    if (!"".equals(jsonStr) && jsonStr!=null) {
        JSONObject jsonObject = (JSONObject) JSONObject.parseObject(jsonStr,objectClass);
        List<T> list = (List<T>) JSONObject.toJavaObject(jsonObject,objectClass);
        return list;
    }
    return null;
}

    /***
     * Object 转为  JSON
     * @param object
     * @return
     */
    public static String object2Json(Object object) {
        if(null != object){
            String jsonObject = JSONObject.toJSONString(object);
            return jsonObject;
        }
        return "";
    }

    /**
     * Map 转 对象
     * @param map
     * @param beanClass
     * @return
     * @throws Exception
     */
    public static Object mapToObject(Map<String, Object> map, Class<?> beanClass) throws Exception {
        if (map == null)
            return null;

        Object obj = beanClass.newInstance();

        BeanUtils.populate(obj, map);

        return obj;
    }

    /**
     * 对象转map
     * @param obj
     * @return
     */
    public static Map<?, ?> objectToMap(Object obj) {
        if(obj == null)
            return null;

        return new BeanMap(obj);
    }


}
