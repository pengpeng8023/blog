package com.study.blog.server.service;

import java.util.Map;

/**
 * Describe:
 * Created by pengp on 2018/1/15.
 */
public interface IBlogServerService {
    Object invokeWebService(String method, Map paramMap);
}
