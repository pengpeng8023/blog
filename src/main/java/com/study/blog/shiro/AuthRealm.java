package com.study.blog.shiro;

import com.study.blog.server.bo.AccountBo;
import com.study.blog.server.service.IAccountService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by overfly on 2017/6/6.
 */
public class AuthRealm extends AuthorizingRealm {

    @Autowired
    private IAccountService accountService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        AccountBo accountBo = (AccountBo) SecurityUtils.getSubject().getPrincipal();
        //ist<String> moduleList = accountService.findModuleListByUserId(user.getUserId());
        //权限的认证的对象
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //将用户的权限信息,提交给Shiro安全管理器
        //info.addStringPermissions(moduleList);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) {
        //强制转换
        /**
         * 验证思路
         * 通过用户传入的accountNo 去数据库中查找对象
         * 把对象传给shiro  shiro自动的根据对象中的密码做匹配
         */
        UsernamePasswordToken loginToken = (UsernamePasswordToken) authenticationToken;
        //获得用户传入的username
        String accountNo = loginToken.getUsername();

        //通过username查询出对象
        AccountBo accountBo = accountService.getAccountByAccountNo(accountNo);

        /**
         * shiro安全认证的参数
         * 1.查询回来的对象
         * 2.正确的密码  user.getPassword()
         * 3.当前info对象的名称
         */
        AuthenticationInfo info = new SimpleAuthenticationInfo(accountBo,accountBo.getAccountPwd(),this.getName());

        //当info对象创建完成后shiro 会在内部做比较  如果传入的密码和数据中的密码相同则放行 否则抛出异常
        return info;
    }
}
