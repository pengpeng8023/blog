package com.study.blog.loginAndRegister.service;


import java.util.Map;

/**
 * Describe:
 * Created by pengp on 2018/2/5.
 */
public interface ILoginAndRegisterService {
    Object register(Map paramMap);

    Integer getCountByAccountNo(String accountNo);

    Object getLogAccountInfo(Map paramMap);
}
