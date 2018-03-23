package com.study.blog.redis;

import java.util.List;
import java.util.Map;

/**
 * Describe:
 * Created by pengp on 2018/1/18.
 */
public interface IRedisService {

    public boolean set(String key, String value);

    public String get(String key);

    public boolean expire(String key,long expire);

    public <T> boolean setList(String key ,List<T> list);

    public <T> List<T> getList(String key, Class<T> clz);

    public long lpush(String key,Object obj);

    public long rpush(String key,Object obj);

    public String lpop(String key);

    public <K,V> Map<K,V> getMap(String key,Class<V> clz);

    public <K,V> boolean setMap(String key, Map<K,V> map);

}
