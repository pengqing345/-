package com.pq.dao;

import com.pq.pojo.Role;
import com.pq.pojo.RoleRelation;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PowerMapper {

    List<Role> selectAll();

    String selectByRoleId(@Param("roleId")String roleId);

    int countByRoleId(@Param("roleId")String roleId);

    List<String> selectUserName(@Param("roleId")String roleId);

    int insertRelation(@Param("roleRelation")RoleRelation roleRelation);
}
