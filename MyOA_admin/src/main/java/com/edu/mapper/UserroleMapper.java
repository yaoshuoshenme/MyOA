package com.edu.mapper;

import com.edu.pojo.User;
import com.edu.pojo.Userrole;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserroleMapper {
    int deleteByUid(Integer id);

    int insert(Userrole record);

    int insertByUidRid(@Param("uid") Integer uid,@Param("rid") Integer rid);

    Userrole selectByUid(Integer id);

    int updateByUserid(@Param("uid")Integer uid,@Param("roleid")Integer roleid);

    List<Integer> queryByUid(Integer uid);

    Userrole queryByUidRid(@Param("uid") Integer uid,@Param("rid") Integer rid);

}