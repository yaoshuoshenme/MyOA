package com.edu.service;

import com.edu.commons.vo.MenuVo;
import com.edu.commons.vo.PageVo;
import com.edu.pojo.Resource;

import java.util.List;

public interface ResourceService {
    List<MenuVo> selectMenu(Integer uid);
    List<Resource> queryParentMenu(Integer uid);

    boolean saveMenu(Resource resource);

    PageVo<Resource> resourcelist(Integer page,Integer limit);

    List<String> selectByuid(Integer uid);
}
