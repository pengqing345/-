package com.pq.service.impl;

import com.pq.dao.DeptMapper;
import com.pq.pojo.Dept;
import com.pq.service.DeptService;
import com.pq.utils.GetRandon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;

    //所有部门
    @Override
    public List<Dept> selectAllDept() {
        List<Dept> depts = deptMapper.selectAll();
        for (Dept dept : depts) {
            dept.setCount(count(dept.getDeptId()));
            dept.setJobName(selectByDeptId(dept.getDeptId()));
        }
        return depts;
    }

    //部门下的总人数
    @Override
    public Integer count(String deptId) {
        return deptMapper.selectCount(deptId);
    }

    //删除部门
    @Override
    public Integer delByDeptId(String deptId) {
        return deptMapper.delByDeptId(deptId);
    }

    //增加部门
    @Override
    public Integer insertDept(Dept dept) {
        String deptId = GetRandon.getRandom(16);
        dept.setDeptId(deptId);
        return deptMapper.insertDept(dept);
    }

    // 查询部门下的职位
    @Override
    public List<String> selectByDeptId(String deptId) {
        return deptMapper.selectByDeptId(deptId);
    }

    //跟新部门信息
    @Override
    public Integer updateByDeptId(Dept dept) {
        return deptMapper.updateByDeptId(dept);
    }
}
