package com.edu.web.controller;

import com.edu.commons.vo.JsonModel;
import com.edu.commons.vo.PageVo;
import com.edu.pojo.Role;
import com.edu.pojo.User;
import com.edu.service.RoleService;
import com.edu.service.UserRoleService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class RoleController {
    @Autowired
    private RoleService rs;
    @Autowired
    private UserRoleService urs;

    //添加角色
    @RequestMapping("saverole.do")
    public String saverole(Role role){
        if(rs.rolesave(role)){
            return "redirect:rolelist.html";
        }else{
            return "redirect:roleadd.html";
        }
    }

    //查询所有角色
    @RequestMapping("rolelist.do")
    @ResponseBody
    public PageVo<Role> rolelist(Integer page, Integer limit){
        return rs.queryRoleList(page,limit);
    }

    //修改用户角色
    @RequestMapping("userrolemodify.do")
    @ResponseBody
    public JsonModel userrolemodify(Integer uid,Integer[] rid){
//        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
        if(urs.updateByUsername(uid,rid)){
            return JsonModel.ok();
        }else{
            return JsonModel.error();
        }
    }
    //查询所有角色不分页
    @RequestMapping("queryallroles.do")
    @ResponseBody
    public List<Role> queryallroles(){
        return rs.queryRoles();
    }

    //根据uid查角色
    @RequestMapping("/queryridbyuid.do")
    @ResponseBody
    public List<Integer> queryridbyuid(Integer uid){
        List<Integer> roles = urs.queryByUid(uid);
        return roles;
    }

}
