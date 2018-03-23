package com.study.blog.server.bo;

import java.io.Serializable;

/**
 *
 * @ClassName: ServerConfigBo
 * @Description: TODO(服务统一配置表)
 *
 */
public class ServerConfigBo implements Serializable {

    /**
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
     */
    private static final long serialVersionUID = -5938625803367761215L;

    private String id;

    private String serverType;

    private String wsParamType;

    private String classPath;

    private String classFunc;

    private String classFuncDesc;

    private String extend1;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getServerType() {
        return serverType;
    }

    public void setServerType(String serverType) {
        this.serverType = serverType;
    }

    public String getWsParamType() {
        return wsParamType;
    }

    public void setWsParamType(String wsParamType) {
        this.wsParamType = wsParamType;
    }

    public String getClassPath() {
        return classPath;
    }

    public void setClassPath(String classPath) {
        this.classPath = classPath;
    }

    public String getClassFunc() {
        return classFunc;
    }

    public void setClassFunc(String classFunc) {
        this.classFunc = classFunc;
    }

    public String getClassFuncDesc() {
        return classFuncDesc;
    }

    public void setClassFuncDesc(String classFuncDesc) {
        this.classFuncDesc = classFuncDesc;
    }

    public String getExtend1() {
        return extend1;
    }

    public void setExtend1(String extend1) {
        this.extend1 = extend1;
    }

}
