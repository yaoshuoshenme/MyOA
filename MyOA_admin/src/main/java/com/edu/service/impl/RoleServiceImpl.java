package com.edu.service.impl;

import com.edu.commons.vo.PageVo;
import com.edu.mapper.RoleMapper;
import com.edu.pojo.Role;
import com.edu.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper rm;

    @Override
    public boolean rolesave(Role role) {
        return rm.insert(role) > 0;
    }

    @Override
    public PageVo<Role> queryRoleList(Integer pagecurrent, Integer pagesize) {

            Integer pagestart = (pagecurrent - 1)*pagesize;
            return PageVo.createPage(rm.selectAll(pagestart,pagesize),rm.selectCount());
    }

    @Override
    public List<Role> queryRoles() {
        return rm.SelectRoles();
    }
}
