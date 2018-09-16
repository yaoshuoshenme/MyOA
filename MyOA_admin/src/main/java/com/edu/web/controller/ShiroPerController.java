package com.edu.web.controller;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class ShiroPerController {

    @RequestMapping("/shiropercheck.do")
    public void shiropercheck(String per, HttpServletResponse response){
        try {
            SecurityUtils.getSubject().checkPermission(per);
            response.getWriter().print(0);
        }catch (Exception e){
            try {
                response.getWriter().print(1);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

    }
}
