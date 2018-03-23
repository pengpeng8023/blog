package com.study.blog.server.mapper;

import com.study.blog.server.bo.UserBo;

/**
 * Describe:
 * Created by pengp on 2018/1/15.
 */
public interface IUserUtilMapper {
    UserBo getUserBo(String accountNo);

    int insertUserBo(UserBo bo);
}
