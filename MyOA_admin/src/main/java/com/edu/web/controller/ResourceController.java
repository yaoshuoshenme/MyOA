package com.edu.web.controller;

import com.edu.commons.vo.MenuVo;
import com.edu.commons.vo.PageVo;
import com.edu.pojo.Resource;
import com.edu.pojo.User;
import com.edu.service.ResourceService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ResourceController {
    @Autowired
    private ResourceService rs;

    //查询菜单
    @RequestMapping("getMenu.do")
    @ResponseBody
    public List<MenuVo> getMenu(HttpSession session){
        User user = (User) session.getAttribute("user");
        if(null != user){
            List<MenuVo> menuVo = rs.selectMenu(user.getId());
            if(null != menuVo && menuVo.size() > 0){
                return menuVo;
            }
        }
        return null;
    }

    //查询一级菜单
    @GetMapping("parentmenu.do")
    @ResponseBody
    public List<Resource> parentmenu(){
        //获取uid
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
        //调service查一级菜单
        List<Resource> resources = rs.queryParentMenu(user.getId());
        return resources;
    }

    //保存添加菜单
    @RequestMapping("savemenu.do")
    public String savemenu(Resource resource){
        if(rs.saveMenu(resource)){
            return "successadd.html";
        }else{
            return "falseadd.html";
        }
    }
    //查询资源列表
    @RequestMapping("resourcelist.do")
    @ResponseBody
    public PageVo<Resource> resourcelist(@RequestParam("page") Integer currentpage, @RequestParam("limit") Integer pagesize){
        return rs.resourcelist(currentpage,pagesize);
    }
}
