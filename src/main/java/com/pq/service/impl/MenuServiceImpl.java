package com.pq.service.impl;

import com.pq.dao.MenuMapper;
import com.pq.pojo.ChildrenMenu;
import com.pq.pojo.Menu;
import com.pq.service.MenuService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MenuServiceImpl  implements MenuService {
    @Autowired
    private MenuMapper menuMapper;
    //查询所有一级菜单
    @Override
    public List<Menu> getFirstLevelMenusByUserId(String userId) {
        List<Menu> firstLevelMenusByUserId = menuMapper.getFirstLevelMenusByUserId(userId);
        for (Menu m:firstLevelMenusByUserId) {
            m.setChildrenMenu(getMenusByParentId(m.getMenuId()));
        }
        return firstLevelMenusByUserId;
    }
    //查询一级菜单下的二级菜单
    @Override
    public List<ChildrenMenu> getMenusByParentId(String parentId) {
        return menuMapper.getMenusByParentId(parentId);
    }
}
