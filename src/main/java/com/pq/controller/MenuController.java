package com.pq.controller;


import com.pq.pojo.Menu;
import com.pq.pojo.User;
import com.pq.service.MenuService;
import com.pq.utils.ResultContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@RequestMapping(value = "/user/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public ResultContent getFirstLevelMenusByUserId(String userId){
        List<Menu> firstLevelMenusByUserId = menuService.getFirstLevelMenusByUserId(userId);
        return  new ResultContent(0,"success",firstLevelMenusByUserId);
    }

}
