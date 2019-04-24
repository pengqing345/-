package com.pq.service.impl;

import com.pq.dao.PowerMapper;
import com.pq.dao.UserMapper;
import com.pq.pojo.Emp;
import com.pq.pojo.Role;
import com.pq.pojo.RoleRelation;
import com.pq.pojo.User;
import com.pq.service.PowerService;
import com.pq.utils.GetRandon;
import com.pq.utils.ResultContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PowerServiceImpl implements PowerService {

    @Autowired
    private PowerMapper powerMapper;
    @Autowired
    private UserMapper userMapper;


    //所有角色信息
    @Override
    public ResultContent selectAll() {
        List<Role> roles = powerMapper.selectAll();
        for (Role role : roles) {
            role.setUserNum(powerMapper.countByRoleId(role.getRoleId()));
            role.setUserName(powerMapper.selectUserName(role.getRoleId()));
        }
        return new ResultContent(0, "", roles);
    }


    //插入角色与用户关联信息
    @Override
    public ResultContent insertUserRelation(String roleId, String userId) {
        RoleRelation roleRelation = new RoleRelation();
        roleRelation.setUserRoleId(GetRandon.getRandom(16));
        roleRelation.setRoleId(roleId);
        roleRelation.setUserId(userId);
        roleRelation.setRemark(powerMapper.selectByRoleId(roleId) + "-" + userMapper.selectUserName(userId) + "关联信息");
        int i = powerMapper.insertRelation(roleRelation);
        return new ResultContent(0, "", i);
    }

    //查询具体角色的信息
    @Override
    public ResultContent selectByRoleId(String roleId) {
        return new ResultContent(0, "", powerMapper.selectUserName(roleId));
    }

    //删除角色关联信息
    @Override
    public ResultContent delRelation(String userName,String roleId) {
        return new ResultContent(0, "", powerMapper.delRelation(userName , roleId));
    }

    //查询没有在当前角色的用户
    @Override
    public ResultContent selectForOtherEmp(String roleId) {
        int i = 0;
        List<String> newUserName = new ArrayList<>();
        List<String> userNames = powerMapper.selectUserName(roleId);
        List<User> users = userMapper.selectAll();
        for (User user : users) {
            for (String userName : userNames) {
                if (user.getUserName().equals(userName)) {
                    i++;
                }
            }
            if(i == 0){
                newUserName.add(user.getUserName());
            }
            i = 0;
        }
        return new ResultContent(0, "", newUserName);
    }
}
