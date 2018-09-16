package com.edu.service;

import com.edu.commons.vo.PageVo;
import com.edu.pojo.User;

import java.util.List;

public interface UserService {
    User login(String name, String password);

    boolean save(User user);
    PageVo<User> queryUserList(Integer currentpage,Integer pagesize);

    boolean checkName(String name);
}
