package com.edu.mapper;

import com.edu.commons.vo.MenuVo;
import com.edu.commons.vo.PageVo;
import com.edu.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    int deleteById(Integer id);

    int insert(User record);


    User selectById(Integer id);


    int updateById(User record);

    User selectByNamePassword(@Param("name") String name, @Param("password") String password);

    List<User> selectAll(@Param("pagestart") Integer pagestart, @Param("pagesize") Integer pagesize);
    int selectCount();

    User selectByName(String name);
}