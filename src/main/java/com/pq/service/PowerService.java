package com.pq.service;

import com.pq.utils.ResultContent;
import org.apache.ibatis.annotations.Param;

public interface PowerService {
    //查询所有角色
    ResultContent selectAll();

    //添加用户的权限
    ResultContent insertUserRelation(String roleId,String userId);
}
