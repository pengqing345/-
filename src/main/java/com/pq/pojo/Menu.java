package com.pq.pojo;

import java.util.List;

public class Menu {
    private String menuId;
    private String menuName;
    private String parentId;
    private String menuURL;
    private String menuICON;
    private String permission;
    private String remark;
    private List<ChildrenMenu> childrenMenu;


    public List<ChildrenMenu> getChildrenMenu() {
        return childrenMenu;
    }

    public void setChildrenMenu(List<ChildrenMenu> childrenMenu) {
        this.childrenMenu = childrenMenu;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getMenuURL() {
        return menuURL;
    }

    public void setMenuURL(String menuURL) {
        this.menuURL = menuURL;
    }

    public String getMenuICON() {
        return menuICON;
    }

    public void setMenuICON(String menuICON) {
        this.menuICON = menuICON;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
