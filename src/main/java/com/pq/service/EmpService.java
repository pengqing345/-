package com.pq.service;

import com.pq.pojo.Emp;

import java.util.List;

public interface EmpService {
      Emp selectByUserId(String userId);

      String selectJobName(String empId);

      List<Emp> selectAllEmp();
}
