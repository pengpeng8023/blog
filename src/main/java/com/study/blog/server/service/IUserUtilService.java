package com.study.blog.server.service;

import com.study.blog.server.bo.UserBo;

import javax.servlet.http.HttpSession;

/**
 * Describe:
 * Created by pengp on 2018/1/15.
 */
public interface IUserUtilService {
    public UserBo getUserBo(HttpSession session);
}
