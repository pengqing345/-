package com.pq.dao;

import com.pq.pojo.Dept;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeptMapper {
    List<Dept> selectAll();

    Integer selectCount(@Param("deptId") String deptId);

    Integer delByDeptId(@Param("deptId") String deptId);

    Integer insertDept(Dept dept);

    List<String> selectByDeptId(@Param("deptId") String deptId);

    Integer updateByDeptId(Dept dept);
}
