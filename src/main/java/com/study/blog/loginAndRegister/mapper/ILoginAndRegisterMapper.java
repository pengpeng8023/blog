package com.study.blog.loginAndRegister.mapper;


import org.apache.ibatis.annotations.Select;

import java.util.Map;

/**
 * Describe:
 * Created by pengp on 2018/2/5.
 */
public interface ILoginAndRegisterMapper {
    Object register(Map paramMap);

    Integer getCountByAccountNo(String accountNo);

    String queryTest(String id);

    void saveTestData(Map map);
}
