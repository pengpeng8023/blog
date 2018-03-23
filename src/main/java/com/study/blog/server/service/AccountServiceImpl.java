package com.study.blog.server.service;

import com.study.blog.server.bo.AccountBo;
import com.study.blog.server.mapper.IAccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Describe:
 * Created by pengp on 2018/2/5.
 */
@Service
public class AccountServiceImpl implements IAccountService {
    @Autowired
    private IAccountMapper accountMapper;
    @Override
    public AccountBo getAccountByAccountNo(String accountNo) {
        return accountMapper.getAccountByAccountNo(accountNo);
    }
}
