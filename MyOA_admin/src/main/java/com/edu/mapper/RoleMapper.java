package com.edu.mapper;

import com.edu.pojo.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);


    Role selectById(Integer id);

    List<Role> selectAll(@Param("pagestart") Integer pagestart, @Param("pagesize") Integer pagesize);
    int updateByPrimaryKey(Role record);
    int selectCount();

    List<Role> SelectRoles();
}