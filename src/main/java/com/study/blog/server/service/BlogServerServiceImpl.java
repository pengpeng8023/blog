package com.study.blog.server.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.study.blog.redis.IRedisService;
import com.study.blog.server.bo.ServerConfigBo;
import com.study.blog.server.bo.SqlType;
import com.study.blog.server.mapper.IBlogServerMapper;
import com.study.blog.server.utils.ReflectTools;
import com.study.blog.tool.JsonUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;

/**
 * Describe:
 * Created by pengp on 2018/1/18.
 */
@Service
public class BlogServerServiceImpl implements IBlogServerService {

    private static Logger logger = Logger
            .getLogger(BlogServerServiceImpl.class);

    //服务错误编码
    private final String SERVER_ERROR_CODE = "1000";
    //脚本错误编码
    private final String SQL_ERROR_CODE = "2000";
    private final String SERVER_RETURN_DATA = "data";
    private final String SERVER_CUST = "CUST";
    // 配置服务信息缓存前缀
    private final String SERVER_REST_PREFIX = "BLOG_SERVER_";
    // 自定义SQL数据缓存前缀
    private final String SERVER_CUST_PARAM_PREFIX = "CUST_DAO_PARAM_";
    // 输出参数
    //private final static String SQL_OUT_DATA_NAME = "outdata";
    // 输出消息
    private final static String SQL_OUT_MSG_NAME = "outmsg";

    @Autowired(required = true)
    private IBlogServerMapper dao;
    @Autowired
    private IRedisService redisService;
    /**
     * 后端rest服务逻辑处理统一入口
     */
    @Override
    public Object invokeWebService(String method, Map paramMap)  {
        // 服务配置信息
        ServerConfigBo serverConfigBo = new ServerConfigBo();
        // 脚本及类型对象
        Map<String, String> sqlConfigMap = new HashMap<String, String>();
        // 执行参数集合，包含脚本、入参、出参
        HashMap<String, Object> paramTotal = new HashMap<String, Object>();
        // 执行结果数据
        Object executeValue = "";
        // 最终返回Map数据
        Map<String, Object> returnValue = new HashMap<String, Object>();
        // 最终返回json数据
        String finalValue;

        try {
            /**
             * 获取服务配置信息
             */
            serverConfigBo = getServerConfig(method);

            logger.info(method + " execute inputparam " + paramMap.toString());

            if (SERVER_CUST.equalsIgnoreCase(serverConfigBo.getServerType())) {

                /**
                 * 自定义SQL功能
                 */
                sqlConfigMap = getSqlConfigByEleNo(serverConfigBo.getExtend1());

                paramTotal.put("sql", sqlConfigMap.get("sql"));
                paramTotal.put("paramMap", paramMap);
                try {
                    switch (SqlType.valueOf(SqlType.class,
                            sqlConfigMap.get("sqlType"))) {

                        case SELECT:
                            executeValue = execSqlWithSelect(paramTotal);
                            break;
                        case PROC:
                            executeValue = execSqlWithProc(paramTotal);
                            break;
                        case INSERT:
                            executeValue = execSqlWithInsert(paramTotal);
                            break;
                        case UPDATE:
                            executeValue = execSqlWithUpdate(paramTotal);
                            break;
                        case DELETE:
                            executeValue = execSqlWithDelete(paramTotal);
                            break;
                        default:
                            break;
                    }
                } catch (Exception e) {
                    logger.error(e);
                    executeValue = ReflectTools.getObj(SQL_ERROR_CODE,
                            "执行自定义SQL脚本出错:" + e.getMessage(),
                            sqlConfigMap.get("ele_no"));
                }
            } else {

                /**
                 * invoke service调用
                 */
                executeValue = invokeMethod(serverConfigBo.getClassPath(), method,
                        serverConfigBo.getExtend1(), paramMap);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            executeValue = ReflectTools
                    .getObj(SERVER_ERROR_CODE, "调用接口出错:" + e.getMessage(),
                            serverConfigBo.getWsParamType());
        } finally {

            returnValue.put(SERVER_RETURN_DATA, executeValue);
        }
        finalValue = JSONObject.toJSONString(returnValue,
                SerializerFeature.WriteMapNullValue);
        finalValue = JSON.toJSONString(returnValue, SerializerFeature.DisableCircularReferenceDetect);
//		JSONObject jsonObject = JSONObject.fromObject(returnValue);
//		finalValue = jsonObject.toString();
        logger.info(method + " execute outparam " + finalValue);
        return finalValue;
    }

    /**
     *
     * @param classPath 接口路径
     * @param method 方法名
     * @param extend1 实现类名
     * @param paramMap 入参
     * @return
     * @throws Exception
     */
    public static Object invokeMethod(String classPath,
            String method, String extend1, Map paramMap) throws Exception {
        Class ownerClass = Class.forName(classPath);
        Class clazz = null;
        if (ownerClass.isInterface()) {
            String packageName = ownerClass.getPackage().getName();
            List<Class> allClass = getClasses(packageName);
            for(int i=0;i<allClass.size();i++){
                if(ownerClass.isAssignableFrom(allClass.get(i))&&allClass.get(i).getName().endsWith(extend1)){
                    clazz = allClass.get(i);
                    break;
                }
            }
            Method method1 = clazz.getMethod(method,Map.class);
            return method1.invoke(clazz.newInstance(),paramMap);
        }

        return null;
    }
    /**
     *
     * @Description: 根据包名获得该包以及子包下的所有类不查找jar包中的
     * @param packageName 包名
     * @return List<Class>    包下所有类
     */
    private static List<Class> getClasses(String packageName) throws ClassNotFoundException, IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String path = packageName.replace(".", "/");
        Enumeration<URL> resources = classLoader.getResources(path);
        List<File> dirs = new ArrayList<File>();
        while(resources.hasMoreElements()){
            URL resource = resources.nextElement();
            String newPath = resource.getFile().replace("%20", " ");
            dirs.add(new File(newPath));
        }
        ArrayList<Class> classes = new ArrayList<Class>();
        for(File directory:dirs){
            classes.addAll(findClass(directory, packageName));
        }
        return classes;
    }

    private static  List<Class> findClass(File directory, String packageName)
            throws ClassNotFoundException{
        List<Class> classes = new ArrayList<Class>();
        if(!directory.exists()){
            return classes;
        }
        File[] files = directory.listFiles();
        for(File file:files){
            if(file.isDirectory()){
                assert !file.getName().contains(".");
                classes.addAll(findClass(file, packageName+"."+file.getName()));
            }else if(file.getName().endsWith(".class")){
                classes.add(Class.forName(packageName+"."+file.getName().substring(0,file.getName().length()-6)));
            }
        }
        return classes;
    }
    /**
     *
     * @Title: getServerConfig
     * @Description: TODO(获取服务配置信息)
     * @param @param method
     * @param @return 设定文件
     * @return ServerConfigBo 返回类型
     * @throws
     */
    private ServerConfigBo getServerConfig(String method) throws Exception {
        ServerConfigBo serverConfigBo = null;
        Map cacheObj = null;
        String key = SERVER_REST_PREFIX + method;
        try {
            cacheObj = redisService.getMap(key,String.class);
        } catch (Exception e) {
            logger.error(method
                    + " config get from cache failure , get from database");
            cacheObj = null;
        }
        if (cacheObj == null) {
            logger.info(method + " config  get from database");
            serverConfigBo = dao.getServiceConfig(method);
            redisService.setMap(key, JsonUtils.objectToMap(serverConfigBo));
        } else {
            logger.info(method + " config  get from cache");
            serverConfigBo = (ServerConfigBo) JsonUtils.mapToObject(cacheObj,ServerConfigBo.class);
        }
        if (serverConfigBo == null
                || StringUtils.isEmpty(serverConfigBo.getId())){
            throw new RuntimeException("未配置 P_SERVER_CONFIG表中" + method
                    + "方法信息或未启用");
        }
        return serverConfigBo;
    }

    /**
     *
     * @Title: getServerConfig
     * @Description: TODO(获取sql脚本配置信息)
     * @param @param eleNo
     * @param @return 设定文件
     * @return ServerConfigBo 返回类型
     * @throws
     */
    private Map<String, String> getSqlConfigByEleNo(String eleNo)
            throws Exception {
        Map<String, String> sqlConfigMap = null;
        String key = SERVER_CUST_PARAM_PREFIX + eleNo;
        try {
            sqlConfigMap = redisService.getMap(key,String.class);
        } catch (Exception e) {
            logger.error(eleNo
                    + " config get from cache failure , get from database");
            sqlConfigMap = null;
        }
        if (sqlConfigMap == null) {
            logger.info(eleNo + " config  get from database");
            sqlConfigMap = dao.getSqlConfig(eleNo);
            redisService.setMap(key, sqlConfigMap);
        }
        if (sqlConfigMap == null
                || StringUtils.isEmpty(sqlConfigMap.get("sql"))){throw new RuntimeException("未配置 P_SQL_CONFIG表中" + eleNo + "配置信息或未启用");
        }

        return sqlConfigMap;
    }

    /**
     *
     * @Title: execSqlWithSelect
     * @Description: TODO(获取查询SQL执行结果)
     * @param @param selectSQL
     * @param @param paramMap
     * @param @return 设定文件
     * @return List 返回类型
     * @throws
     */
    @SuppressWarnings("unused")
    private List<HashMap<String, Object>> execSqlWithSelect(
            HashMap<String, Object> paramTotal) throws Exception {

        return dao.execSqlWithSelect(paramTotal);
    }

    /**
     *
     * @Title: execSqlWithProc
     * @Description: TODO(执行存储过程)
     * @param @param procSQL
     * @param @param paramMap
     * @param @return 设定文件
     * @return Object 返回类型
     * @throws
     */
    @SuppressWarnings("unused")
    private HashMap<String, Object> execSqlWithProc(
            HashMap<String, Object> paramTotal) throws Exception {

        dao.execSqlWithProc(paramTotal);
        if (paramTotal.containsKey(SQL_OUT_MSG_NAME)){
            logger.info(paramTotal.get(SQL_OUT_MSG_NAME));
        }

        paramTotal.remove("sql");

        return paramTotal;
    }

    /**
     *
     * @Title: execSqlWithInsert
     * @Description: TODO(执行插入语句)
     * @param @param procSQL
     * @param @param paramMap
     * @param @return 设定文件
     * @return Object 返回类型
     * @throws
     */
    private int execSqlWithInsert(HashMap<String, Object> paramTotal)
            throws Exception {

        return dao.execSqlWithInsert(paramTotal);

    }

    /**
     *
     * @Title: execSqlWithUpdate
     * @Description: TODO(执行更新语句)
     * @param @param procSQL
     * @param @param paramMap
     * @param @return 设定文件
     * @return Object 返回类型
     * @throws
     *
     */
    private int execSqlWithUpdate(HashMap<String, Object> paramTotal)
            throws Exception {
        return dao.execSqlWithUpdate(paramTotal);

    }

    /**
     *
     * @Title: execSqlWithDelete
     * @Description: TODO(执行删除语句)
     * @param @param procSQL
     * @param @param paramMap
     * @param @return 设定文件
     * @return Object 返回类型
     * @throws
     *
     */
    private int execSqlWithDelete(HashMap<String, Object> paramTotal)
            throws Exception {
        return dao.execSqlWithDelete(paramTotal);

    }

}

