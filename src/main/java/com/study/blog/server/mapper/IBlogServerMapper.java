package com.study.blog.server.mapper;

import com.study.blog.server.bo.ServerConfigBo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Describe:
 * Created by pengp on 2018/1/30.
 */
public interface IBlogServerMapper {
    ServerConfigBo getServiceConfig(String method);

    Map<String,String> getSqlConfig(String eleNo);

    List<HashMap<String,Object>> execSqlWithSelect(HashMap<String, Object> paramTotal);

    void execSqlWithProc(HashMap<String, Object> paramTotal);

    int execSqlWithInsert(HashMap<String, Object> paramTotal);

    int execSqlWithUpdate(HashMap<String, Object> paramTotal);

    int execSqlWithDelete(HashMap<String, Object> paramTotal);
}
