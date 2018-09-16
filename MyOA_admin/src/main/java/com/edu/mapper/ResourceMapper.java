package com.edu.mapper;

import com.edu.commons.vo.MenuVo;
import com.edu.commons.vo.PageVo;
import com.edu.pojo.Resource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResourceMapper {
    int deleteByRid(Integer id);

    int insert(Resource record);

    Resource selectByRid(Integer id);

    int updateByRid(Resource record);

    int selectcount();

    List<Resource> selectMenu(Integer uid);
    List<Resource> selectParentMenu(Integer uid);
    List<Resource> selectPageVo(@Param("pagestart") Integer pagestart, @Param("limit") Integer limit);
    List<String> selectByuid(Integer uid);
}