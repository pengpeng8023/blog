package com.study.blog.server.controller;

import com.study.blog.server.bo.UserBo;
import com.study.blog.server.service.IBlogServerService;
import com.study.blog.server.service.IUserUtilService;
import com.study.blog.server.utils.ReflectTools;
import com.study.blog.tool.VerifyCodeUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Describe:
 * Created by pengp on 2018/1/15.
 */
@Controller
@RequestMapping("/blog/")
public class BlogServerController {
    /**
     * 日志
     */
    private static Logger logger = Logger.getLogger(BlogServerController.class);

    @Autowired
    private IBlogServerService service;

    /**
     * 用户服务
     */
    @Autowired
    private IUserUtilService userUtilService;

    /**
     * 页面跳转
     * @param page
     * @param session
     * @param request
     * @return
     */
    @RequestMapping("{page}")
    public String toLogin(@PathVariable String page, HttpSession session, HttpServletRequest request) {
        String path = BlogServerController.class.getClassLoader().getResource("").getPath();
        File dir = new File(path+"staticfile/template");
        int w = 155, h = 40;
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
        File file = new File(dir, "verifyCode.jpg");
        VerifyCodeUtils.outputImage(w, h, file, verifyCode);
        session.setAttribute("verCode",verifyCode.toLowerCase());
        if(page.indexOf(".")>0 && page.endsWith(".html")){
            page = page.substring(0,page.indexOf("."));
        }else if(page.indexOf(".")>0 || page.indexOf("#")>0){
            return "redirect:/blog/login";
        }
            return page;
    }
    /**
     *
     * @Title: invokeWebService
     * @Description: TODO(请求统一入口--不跨域)
     * @param @param method
     * @param @param request
     * @param @param response
     * @param @return
     * @param @throws Exception    设定文件
     * @return Object    返回类型
     * @throws
     */
    @RequestMapping(value = {"/invokeWebService/{method}"})
    @ResponseBody
    @SuppressWarnings("rawtypes")
    public Object invokeWebService(@PathVariable("method") String method, HttpServletRequest request, HttpServletResponse response) throws Exception{
        logger.info("no cross request "+method);
        Map paramMap = ReflectTools.getParameterMap(request);
        extendParamMap(paramMap,request.getSession());
        Object result = service.invokeWebService(method, paramMap);
        return result;
    }

    /**
     *
     * @Title: invokeWebServiceCross
     * @Description: TODO(请求统一入口--跨域)
     * @param @param method
     * @param @param request
     * @param @param response
     * @param @throws Exception    设定文件
     * @return void    返回类型
     * @throws
     */
    @RequestMapping(value = {"/invokeWebServiceCross/{method}"})
    @SuppressWarnings("rawtypes")
    public void invokeWebServiceCross(@PathVariable("method") String method,HttpServletRequest request,HttpServletResponse response) throws Exception{
        logger.info("cross request "+method);
        Map paramMap = ReflectTools.getParameterMap(request);
        extendParamMap(paramMap,request.getSession());
        Object result = service.invokeWebService(method, paramMap);
        String callbackFun = (String) paramMap.get("callbackparam");
        response.getWriter().write(callbackFun+"('"+result+"')");
        response.getWriter().close();
    }
    /**
     *
     * @Title: extendParamMap
     * @Description: TODO(入参对象操作)
     * @param @param paramMap    设定文件
     * @return void    返回类型
     * @throws
     */
    private void extendParamMap(Map paramMap,HttpSession session){
        if(paramMap == null){
            paramMap = new HashMap<>();
        }
        UserBo userBo = null;
        try{
            userBo = userUtilService.getUserBo(session);
            if (userBo!=null){
                paramMap.put("logUserNo", userBo.getUserNo());
                paramMap.put("logUserName", userBo.getUserName());
                paramMap.put("logAccountNo", userBo.getAccountNo());
                paramMap.put("logAccountName", userBo.getAccountName());
                paramMap.put("logUserEmail",userBo.getAccountEmail());
                paramMap.put("logUserPhone",userBo.getAccountPhone());
                paramMap.put("logRoleNo",userBo.getAccountRoleNo());
                paramMap.put("logRoleName",userBo.getAccountRoleName());
            }
        }catch(Exception e){
            logger.error("----extendParamMap-----  出错信息"+e.getMessage());;
            logger.error("execute getUserBo error! use default admin toLogin");
        }

    }

}
