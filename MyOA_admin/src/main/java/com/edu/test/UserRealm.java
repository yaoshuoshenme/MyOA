package com.edu.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class UserRealm extends AuthorizingRealm {
    //授权。提供当前用户的角色和权限
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    //认证。登录校验
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取令牌
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        //外界已进行过登录校验，次数只需进行对象封装
        if(token.getUsername().length()>0){
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo();
            //存储到session
//            SecurityUtils.getSubject().getSession().setAttribute("username",token.getUsername());
            return info;
        }

        return null;
    }
}
