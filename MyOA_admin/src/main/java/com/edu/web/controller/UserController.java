package com.edu.web.controller;

import com.edu.commons.vo.PageVo;
import com.edu.pojo.User;
import com.edu.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService us;
    //登录
    @RequestMapping("userlogin.do")
    public String login(String name, String password, @RequestParam(defaultValue ="")String remberme, HttpSession session){
        User user = us.login(name, password);
        if(null != user){
            Subject subject = SecurityUtils.getSubject();
            boolean res = false;
            if(remberme.length() > 0){
                res = true;
            }
            UsernamePasswordToken token = new UsernamePasswordToken(name,password,res);
            subject.login(token);
            subject.getSession().setAttribute("user",user);
            session.setAttribute("user",user);

            return "redirect:index.html";
        }
        return "login.html";
    }

    //注销登录
    @RequestMapping("logout.do")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        SecurityUtils.getSubject().getSession().removeAttribute("user");
        return "login.html";
    }

    //新增用户
    @PostMapping("saveuser.do")
    public String saveuser(User user){
        if(us.save(user)){
            return "redirect:userlist.html";
        }else{
            return "redirect:falseadd.html";
        }
    }

    //用户列表
    @GetMapping("userlist.do")
    @ResponseBody
    public PageVo<User> userlist(@RequestParam("page") Integer currentpage,@RequestParam("limit") Integer pagesize){
       return us.queryUserList(currentpage, pagesize);
    }

    //用户名是否存在
    @RequestMapping("checkName.do")
    public void checkName(String name, HttpServletResponse response) throws IOException {
        if(us.checkName(name)){
            response.getWriter().print(0);
        }else{
            response.getWriter().print(1);
        }
    }
}
