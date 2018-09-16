package com.edu.test;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class UserRealm3 extends AuthorizingRealm {
   //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        return info;
    }
    //验证登录
    //已登录过只需封装对象
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        if(token.getUsername().length() > 0){
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(token.getUsername(),token.getPassword(),getName());

            return info;
        }
        return null;
    }
}
