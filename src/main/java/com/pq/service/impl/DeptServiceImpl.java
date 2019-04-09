package com.pq.service.impl;

import com.pq.dao.DeptMapper;
import com.pq.pojo.Dept;
import com.pq.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;

    @Override
    public List<Dept> selectAllDept() {
        List<Dept> depts = deptMapper.selectAll();
        for (Dept dept:depts) {
            dept.setCount(count(dept.getDeptId()));
        }
        return depts;
    }

    @Override
    public Integer count(String deptId) {
        return deptMapper.selectCount(deptId);
    }
}
