package com.study.blog.server.service;

import com.study.blog.server.bo.AccountBo;

/**
 * Describe:
 * Created by pengp on 2018/2/5.
 */
public interface IAccountService {
    AccountBo getAccountByAccountNo(String accountNo);
}
