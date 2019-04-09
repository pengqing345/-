package com.pq.service.impl;

import com.pq.dao.UserMapper;
import com.pq.pojo.Emp;
import com.pq.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmpServiceImpl implements EmpService {
    @Autowired
    private UserMapper userMapper;
    //查询用户的个人信息
    @Override
    public Emp selectByUserId(String userId) {
        Emp emp = userMapper.selectByUserId(userId);
        String jobName = selectJobName(emp.getEmpId());
        emp.setJobName(jobName);
        return emp;
    }
  //查询部门名字
    @Override
    public String selectJobName(String empId) {
        return userMapper.selectJobName(empId);
    }

    //查询所有个人信息
    @Override
    public List<Emp> selectAllEmp() {
        List<Emp> emps = userMapper.selectAllEmp();
        for (Emp emp:emps) {
          emp.setJobName(selectJobName(emp.getEmpId()));
        }
        return emps;
    }
}
