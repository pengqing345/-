package com.pq.dao;

import com.pq.pojo.Emp;
import com.pq.pojo.EmpRelation;
import com.pq.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    List<User> selectAll();

    int updatePassword(User u);

    String selectUserName(@Param("userId") String userId);

    String selectUserId(@Param("userName") String userName);

    Emp selectByUserId(@Param("userId") String userId);

    String selectDeptName(@Param("empId") String empId);

    String selectDeptId(@Param("deptName")String deptName);

    String selectJobId(@Param("jobName")String jobName);

    String selectJobName(@Param("empId") String empId);

    List<Emp> selectAllEmp();

    Integer delRelation(@Param("empId") String empId);

    Integer delEmp(@Param("empId") String empId);

    Integer updateEmp(@Param("emp") Emp emp, @Param("deptId") String deptId, @Param("jobId") String jobId);

    Integer insertEmp(Emp emp);

    Integer insertRelation(EmpRelation empRelation);

    Integer insertUser(User user);

    Integer updateStatues(String empName);
}
