package com.edu.service;


import java.util.List;

public interface UserRoleService {
    boolean updateByUsername(Integer uid, Integer[] roleid);
    List<Integer> queryByUid(Integer uid);
}
