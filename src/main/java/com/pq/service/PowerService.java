package com.pq.service;

import com.pq.utils.ResultContent;

public interface PowerService {
    //查询所有角色
    ResultContent selectAll();

    //添加用户的权限
    ResultContent insertUserRelation(String roleId,String userId);

    //查询具体角色的信息
    ResultContent selectByRoleId(String roleId);
}
