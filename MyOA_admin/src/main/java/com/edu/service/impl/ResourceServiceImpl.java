package com.edu.service.impl;

import com.edu.commons.vo.MenuVo;
import com.edu.commons.vo.PageVo;
import com.edu.mapper.ResourceMapper;
import com.edu.pojo.Resource;
import com.edu.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResourceServiceImpl implements ResourceService {
    @Autowired
    private ResourceMapper rm;

    @Override
    public List<MenuVo> selectMenu(Integer uid) {
        List<Resource> resources = rm.selectMenu(uid);
        List<MenuVo> menuVos = new ArrayList<MenuVo>();
        for (Resource r : resources) {
            MenuVo menuVo = new MenuVo();
            if (r.getParentid() == -1) {
                menuVo.setParent(r);
                menuVo.setChildren(new ArrayList<>());
                menuVos.add(menuVo);

            } else {
               int index = r.getParentid().intValue();
               for(MenuVo j : menuVos){
                   if(j.getParent().getId().intValue() == index){
                       j.getChildren().add(r);
                   }
               }
            }
        }
        return menuVos;
    }

    @Override
    public List<Resource> queryParentMenu(Integer uid) {
        return rm.selectParentMenu(uid);
    }

    @Override
    public boolean saveMenu(Resource resource) {

        return rm.insert(resource) > 0;
    }

    @Override
    public PageVo<Resource> resourcelist(Integer page, Integer limit) {
        Integer pagestart = (page - 1)*limit;
        List<Resource> resources = rm.selectPageVo(pagestart, limit);
        PageVo<Resource> pageVo = new PageVo<>();
        if(resources != null && resources.size() > 0){
            pageVo.setCode(0);
            pageVo.setCount(rm.selectcount());
            pageVo.setData(resources);
        }
        return pageVo;
    }

    @Override
    public List<String> selectByuid(Integer uid) {
        return rm.selectByuid(uid);
    }
}

