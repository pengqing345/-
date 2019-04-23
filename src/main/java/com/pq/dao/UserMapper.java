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

    String selectUserName(@Param("userId") String userId);

    Emp selectByUserId(@Param("userId") String userId);

    String selectDeptName(@Param("empId") String empId);

    String selectJobName(@Param("empId") String empId);

    List<Emp> selectAllEmp();

    Integer delRelation(@Param("empId") String empId);

    Integer delEmp(@Param("empId") String empId);
}
