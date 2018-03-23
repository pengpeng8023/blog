package com.study.blog.server.mapper;

import com.study.blog.server.bo.AccountBo;

/**
 * Describe:
 * Created by pengp on 2018/2/5.
 */
public interface IAccountMapper {
    AccountBo getAccountByAccountNo(String accountNo);

    String getAccountNoByUserName(String accountNo);
}
