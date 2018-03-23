package com.study.blog.server.service;

import com.mysql.jdbc.StringUtils;
import com.study.blog.redis.IRedisService;
import com.study.blog.server.bo.AccountBo;
import com.study.blog.server.bo.UserBo;
import com.study.blog.server.mapper.IUserUtilMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * Describe:
 * Created by pengp on 2018/1/15.
 */
@Service
public class UserUtilServiceImpl implements IUserUtilService {
    @Autowired
    private IUserUtilMapper mapper;
    @Autowired
    private IRedisService redisService;
    @Override
    public UserBo getUserBo(HttpSession session) {
        String accountNo;
        try {
            AccountBo abo = (AccountBo) session.getAttribute("session_account");
            accountNo = abo.getAccountNo();
        }catch (Exception e){
            accountNo = "";
        }
        UserBo bo = new UserBo();
        if (StringUtils.isNullOrEmpty(accountNo)){
            Long times = System.currentTimeMillis();
            accountNo = times.toString();
            String userName = "游客"+accountNo;
            bo.setAccountNo(userName);
            bo.setAccountName(userName);
            bo.setAccountEmail("");
            bo.setAccountPhone("");
            bo.setUserNo(accountNo);
            bo.setUserName(userName);
            bo.setAccountRoleNo("2");
            bo.setAccountRoleName("游客");
            mapper.insertUserBo(bo);
        }else{
            bo = mapper.getUserBo(accountNo);
        }
        return bo;
    }
}
