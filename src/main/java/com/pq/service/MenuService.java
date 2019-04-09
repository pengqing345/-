package com.pq.service;

import com.pq.pojo.ChildrenMenu;
import com.pq.pojo.Menu;

import java.util.List;

public interface MenuService {

    List<Menu> getFirstLevelMenusByUserId(String userId);
    List<ChildrenMenu> getMenusByParentId(String parentId);
}
