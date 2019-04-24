package com.pq.service;

import com.pq.pojo.Emp;

import java.util.List;

public interface EmpService {
      Emp selectByUserId(String userId);

      String selectDeptName(String empId);

      String selectJobName(String empId);

      List<Emp> selectAllEmp();

      Integer delRelation(String empId);

      Integer delEmp(String empId);

      Integer updateEmp(Emp emp,String deptId ,String jobId);
}
