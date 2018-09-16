package com.edu.mapper;

import com.edu.pojo.Leave;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveMapper {

    int insert(Leave record);
    Leave selectByLid(Integer id);

    List<Leave> selectByPage(@Param("page") Integer page, @Param("limit") Integer limit);

    int udateFlagByLid(Integer lid, Integer flag);

    int selectCount();
}