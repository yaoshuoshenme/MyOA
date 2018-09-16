package com.edu.test;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class UserRealm2 extends AuthorizingRealm {
   //授权，权限角色
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }
    //登录校验
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        if(token.getUsername().length()> 0){
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(token.getUsername(), token.getPassword(), getName());

            return info ;
        }
        return null;
    }
}
