package com.edu.service.impl;

import com.edu.mapper.UserroleMapper;
import com.edu.pojo.Role;
import com.edu.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserroleMapper um;
    @Override
    public boolean updateByUsername(Integer uid, Integer[] roleid) {
        try {
            for(Integer rid : roleid){
                if(um.queryByUidRid(uid,rid) == null){
                    um.insertByUidRid(uid,rid);
                }else{
                    um.updateByUserid(uid,rid);
                }

            }
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public List<Integer> queryByUid(Integer uid) {
        return um.queryByUid(uid);
    }
}
