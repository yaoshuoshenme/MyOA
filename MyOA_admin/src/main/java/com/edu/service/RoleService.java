package com.edu.service;

import com.edu.commons.vo.PageVo;
import com.edu.pojo.Role;

import java.util.List;

public interface RoleService {

    boolean rolesave(Role role);
    PageVo<Role> queryRoleList(Integer pagecurrent, Integer pagesize);

    List<Role> queryRoles();
}
