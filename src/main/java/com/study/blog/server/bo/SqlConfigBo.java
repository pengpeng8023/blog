package com.study.blog.server.bo;

import java.io.Serializable;
import java.util.Map;

/**
 * Describe:
 * Created by pengp on 2018/1/15.
 */
public class SqlConfigBo implements Serializable{
    private static final long serivalVersionUID = 6678065125763730094L;
    private String sql;
    private String sqlType;
    private String eleNo;
    private String secneCode;
    private Map paramMap;
    public SqlConfigBo() {
    }
    public SqlConfigBo(String sql, Map paramMap) {
        this.sql = sql;
        this.paramMap = paramMap;

    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getSqlType() {
        return sqlType;
    }

    public void setSqlType(String sqlType) {
        this.sqlType = sqlType;
    }

    public String getEleNo() {
        return eleNo;
    }

    public void setEleNo(String eleNo) {
        this.eleNo = eleNo;
    }

    public String getSecneCode() {
        return secneCode;
    }

    public void setSecneCode(String secneCode) {
        this.secneCode = secneCode;
    }

    public Map getParamMap() {
        return paramMap;
    }

    public void setParamMap(Map paramMap) {
        this.paramMap = paramMap;
    }
}
