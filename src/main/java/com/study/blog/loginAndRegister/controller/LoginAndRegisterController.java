package com.study.blog.loginAndRegister.controller;

import com.study.blog.loginAndRegister.service.ILoginAndRegisterService;
import com.study.blog.redis.IRedisService;
import com.study.blog.server.bo.AccountBo;
import com.study.blog.server.service.IUserUtilService;
import com.study.blog.server.utils.ReflectTools;
import com.study.blog.tool.MD5Hash;
import com.study.blog.tool.PropertiesListenerConfig;
import com.study.blog.tool.VerifyCodeUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Describe:
 * Created by pengp on 2018/2/5.
 */
@Controller
@RequestMapping("/base/")
public class LoginAndRegisterController {
    Logger logger = Logger.getLogger(LoginAndRegisterController.class);
    @Autowired
    private IRedisService redisService;
    @Autowired
    private ILoginAndRegisterService service;
    @RequestMapping("init")
    public String init(HttpSession session, Model model){
        logger.info ("----------verifyCode: "+(String)session.getAttribute("verCode"));
        return "test";
    }

    /**
     * 注册
     * @param request
     * @param session
     * @return
     * @throws Exception
     */
    @RequestMapping("register")
    @ResponseBody
    public Object register(HttpServletRequest request,HttpSession session){
        Map paramMap = ReflectTools.getParameterMap(request);
        if(StringUtils.isEmpty(paramMap.get("verCode"))){
            paramMap.put("code",-1);
            paramMap.put("msg","请输入校验码");
            return paramMap;
        }
        if(StringUtils.isEmpty(paramMap.get("accountNo"))){
            paramMap.put("code",-1);
            paramMap.put("msg","请输入账号");
            return paramMap;
        }
        if(StringUtils.isEmpty(paramMap.get("accountPwd"))){
            paramMap.put("code",-1);
            paramMap.put("msg","请输入密码");
            return paramMap;
        }
        if(StringUtils.isEmpty(paramMap.get("checkPwd"))){
            paramMap.put("code",-1);
            paramMap.put("msg","请输入确认密码");
            return paramMap;
        }
        String verCode = (String) session.getAttribute("verCode");
        if (!paramMap.get("accountPwd").equals(paramMap.get("checkPwd"))){
            paramMap.put("code",-1);
            paramMap.put("msg","两次密码输入不同");
            return paramMap;
        }
        if (verCode==null || !verCode.equalsIgnoreCase((String) paramMap.get("verCode"))){
            paramMap.put("code",-1);
            paramMap.put("msg","验证错误");
            return paramMap;
        }
        paramMap.put("accountPwd", MD5Hash.getMd5Hash((String)paramMap.get("accountPwd"),(String)paramMap.get("accountNo")));
        service.register(paramMap);
        return paramMap;
    }

    /**
     * 登录
     * @param request
     * @param session
     * @return
     * @throws Exception
     */
    @RequestMapping("login")
    @ResponseBody
    public Object login(HttpServletRequest request,HttpSession session){
        Map paramMap = ReflectTools.getParameterMap(request);
        String accountNo = (String) paramMap.get("accountNo");
        String accountPwd = (String) paramMap.get("accountPwd");
        if(StringUtils.isEmpty(accountNo) || StringUtils.isEmpty(accountPwd)){
            paramMap.put("code",-1);
            paramMap.put("msg","请输入账号和密码");
            return paramMap;
        }
        UsernamePasswordToken token = new UsernamePasswordToken(accountNo,accountPwd);
        Subject subject = null;
        try{
            subject = SecurityUtils.getSubject();
            subject.login(token);
        } catch (Exception e){
            logger.info("----login-----  出错信息"+e.getMessage());
            paramMap.put("code",-1);
            paramMap.put("msg","账号或密码错误");
            return paramMap;
        }
        AccountBo accountBo = (AccountBo) subject.getPrincipal();
        session.setAttribute("session_account",accountBo);
        paramMap.put("code",1);
        paramMap.put("msg","success");
        return paramMap;
    }
    /**
     * 退出
     * @return
     */
    @RequestMapping(value="logout")
    public String logout(){
        try {
            //退出
            SecurityUtils.getSubject().logout();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return "redirect:/blog/login";
    }
    /**
     * 校验用户名
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("checkAccountNo")
    @ResponseBody
    public Object checkAccountNo(HttpServletRequest request){
        Map paramMap = ReflectTools.getParameterMap(request);
        Integer num = service.getCountByAccountNo((String) paramMap.get("accountNo"));
        paramMap.put("code",num);
        return paramMap;
    }

    /**
     * 校验验证码
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("checkVerCode")
    @ResponseBody
    public Object checkVerCode(HttpServletRequest request){
        Map paramMap = ReflectTools.getParameterMap(request);
        String verCode1 = (String) paramMap.get("verCode");
        String verCode2 = (String) request.getSession().getAttribute("verCode");
        if (verCode2==null || !verCode2.equals(verCode1)){
            paramMap.put("code",-1);
        }else{
            paramMap.put("code",1);
        }
        return paramMap;
    }

}
