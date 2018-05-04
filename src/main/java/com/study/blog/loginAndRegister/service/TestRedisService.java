package com.study.blog.loginAndRegister.service;

import com.study.blog.loginAndRegister.mapper.ILoginAndRegisterMapper;
import com.study.blog.redis.IRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Describe: 测试雪崩
 * Created by pengp on 2018/4/17.
 */
@Service
public class TestRedisService {
    @Autowired
    private ILoginAndRegisterMapper mapper;
    @Autowired
    IRedisService redisService;
    ConcurrentHashMap<String,String> concurrentHashMap = new ConcurrentHashMap<>();
//    Lock lock = new ReentrantLock();
    public String queryTest(String id){
        return mapper.queryTest(id);
    }

    public String testMethod(String id){
        String value = redisService.get(id);
        if (value == null){
            try {
                value = redisService.get(id);
                if (value == null){
                    value = queryTest(id);
                    System.out.println("从数据库中获取value---------"+Thread.currentThread().getName()+"-------"+value);
                    redisService.set(id,value);
                    redisService.expire(id,120);
                }else {
                    System.out.println("从缓存中获取value---------"+Thread.currentThread().getName()+"-------"+value);
                }
            }finally {
            }
        }else {
            System.out.println("从缓存中获取value---------"+Thread.currentThread().getName()+"-------"+value);
        }
        return value;
    }

    public void saveTestData(List<List> l) {
        for (int i=0;i<l.size();i++){
            for (int j=0;j<l.get(0).size();j++){

                mapper.saveTestData((Map) l.get(i).get(j));
            }
        }
    }
}
