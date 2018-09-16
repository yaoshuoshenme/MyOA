package com.edu.test;

import com.edu.pojo.User;
import com.edu.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class UserController2 {
    @Autowired
    private UserService us;

    @RequestMapping("userlogin.do")
    public String login(String name, String password,@RequestParam(defaultValue ="")String remberme, HttpSession Session){
        User user = us.login(name, password);
        if(null != user){
            //获取主题
            Subject subject = SecurityUtils.getSubject();
            boolean res = false;
            if(remberme != null && remberme.length() >0){
                res = true;
            }
            UsernamePasswordToken token = new UsernamePasswordToken(name,user.getPassword(),res);
            subject.login(token);
            subject.getSession().setAttribute("user",user);
            Session.setAttribute("user",user);

            return "index.html";
        }
        return "login.html";


    }
}
