package com.pq.service;

import com.pq.pojo.Dept;

import java.util.List;

public interface DeptService {
    List<Dept> selectAllDept();
    Integer count(String deptId);
    Integer delByDeptId(String deptId);
    Integer insertDept(Dept dept);
    List<String> selectByDeptId(String deptId);
    Integer updateByDeptId(Dept dept);
}
