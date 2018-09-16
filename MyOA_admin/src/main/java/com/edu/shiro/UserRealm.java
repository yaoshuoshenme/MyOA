package com.edu.shiro;

import com.edu.pojo.Resource;
import com.edu.pojo.User;
import com.edu.service.ResourceService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.List;

public class UserRealm extends AuthorizingRealm {

    private ResourceService rs;
    public UserRealm(ResourceService rs){
        this.rs = rs;
    }

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //获取当前用户
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
        //获取当前用户所有权限
        List<String> per = rs.selectByuid(user.getId());
        //加到info里
        info.addStringPermissions(per);
        return info;
    }

    //校验
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取令牌
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        //对象封装
        if(token.getUsername().length() > 0){
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(token.getUsername(), token.getPassword(), getName());
            return info;
        }
        return null;
    }
}
