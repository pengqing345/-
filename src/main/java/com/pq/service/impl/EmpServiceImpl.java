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
        String deptName = selectDeptName(emp.getEmpId());
        emp.setDeptName(deptName);
        return emp;
    }
  //查询部门名字
    @Override
    public String selectDeptName(String empId) {
        return userMapper.selectDeptName(empId);
    }

    //查询职位名称
    @Override
    public String selectJobName(String empId) {
        return userMapper.selectJobName(empId);
    }
    //查询所有个人信息
    @Override
    public List<Emp> selectAllEmp() {
        List<Emp> emps = userMapper.selectAllEmp();
        for (Emp emp:emps) {
          emp.setDeptName(selectDeptName(emp.getEmpId()));
          emp.setJobName(selectJobName(emp.getEmpId()));
        }
        return emps;
    }
    //删除用户和部门的关系
    @Override
    public Integer delRelation(String empId) {
        return userMapper.delRelation(empId);
    }
    //删除用户信息
    @Override
    public Integer delEmp(String empId) {
         delRelation(empId);
         return userMapper.delEmp(empId);
    }
}
