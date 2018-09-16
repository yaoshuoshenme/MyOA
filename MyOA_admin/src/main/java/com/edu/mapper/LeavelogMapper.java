package com.edu.mapper;

import com.edu.pojo.Leavelog;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeavelogMapper {

    int insert(Leavelog record);



    List<Leavelog> selectByPage(@Param("page")Integer page,@Param("limit")Integer limit);

}