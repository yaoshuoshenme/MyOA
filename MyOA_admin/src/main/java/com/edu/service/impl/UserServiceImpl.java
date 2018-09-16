package com.edu.service.impl;

import com.edu.commons.vo.PageVo;
import com.edu.mapper.UserMapper;
import com.edu.pojo.User;
import com.edu.service.UserService;
import com.edu.shiro.ShiroEncryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper um;

    @Override
    public User login(String name , String password) {

        return um.selectByNamePassword(name,ShiroEncryUtil.md5(password));
    }

    @Override
    public boolean save(User user) {

       user.setPassword(ShiroEncryUtil.md5(user.getPassword()));
        return um.insert(user) > 0;
    }

    @Override
    public PageVo<User> queryUserList(Integer currentpage, Integer pagesize) {
        Integer pagestart = (currentpage - 1)*pagesize;
        List<User> users = um.selectAll(pagestart, pagesize);
        PageVo<User> userPageVo = new PageVo<>();
        userPageVo.setData(users);
        userPageVo.setCount((int)um.selectCount());
        userPageVo.setCode(0);
        userPageVo.setMsg("ok");
        return userPageVo;
    }
    //校验用户名是否存在
    @Override
    public boolean checkName(String name) {
        if(um.selectByName(name) != null){
            return false;
        }else{
            return true;
        }
    }



}
