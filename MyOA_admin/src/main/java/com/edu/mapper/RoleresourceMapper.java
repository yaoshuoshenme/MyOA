package com.edu.mapper;

import com.edu.pojo.Roleresource;
import org.apache.ibatis.annotations.Param;

public interface RoleresourceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Roleresource record);


    Roleresource selectByPrimaryKey(Integer id);


    int updateByPrimaryKey(Roleresource record);

    int updateByUsername(@Param("resourcename")String username,@Param("roleid")Integer roleid);

}