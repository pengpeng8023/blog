package com.study.blog.loginAndRegister.service;

import com.study.blog.loginAndRegister.mapper.ILoginAndRegisterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Describe:
 * Created by pengp on 2018/2/5.
 */
@Service
public class LoginAndRegisterServiceImpl implements ILoginAndRegisterService {
    @Autowired
    private ILoginAndRegisterMapper mapper;

    @Override
    public Object register(Map paramMap) {
        return mapper.register(paramMap);
    }

    @Override
    public Integer getCountByAccountNo(String accountNo) {
        return mapper.getCountByAccountNo(accountNo);
    }

    @Override
    public Object getLogAccountInfo(Map paramMap) {
        return paramMap;
    }

}
