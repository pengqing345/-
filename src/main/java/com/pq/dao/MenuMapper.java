package com.pq.dao;

import com.pq.pojo.ChildrenMenu;
import com.pq.pojo.Menu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuMapper {
     List<Menu> getFirstLevelMenusByUserId(@Param("userId") String userId);
     List<ChildrenMenu> getMenusByParentId(@Param("parentId") String parentId);
}
