package com.pq.service;

import com.pq.pojo.Emp;

public interface EmpService {
      Emp selectByUserId(String userId);

      String selectJobName(String empId);
}
