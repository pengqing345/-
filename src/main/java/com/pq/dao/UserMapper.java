package com.pq.dao;

import com.pq.pojo.Emp;
import com.pq.pojo.User;
import com.pq.utils.ResultContent;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    List<User> selectAll();

    ResultContent updatePassword(User u);

    Emp selectByUserId(@Param("userId") String userId);

    String selectJobName(@Param("empId") String empId);


}
