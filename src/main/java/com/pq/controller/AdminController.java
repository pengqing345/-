package com.pq.controller;

import com.pq.pojo.User;
import com.pq.service.AdminService;
import com.pq.utils.ResultContent;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.annotation.Resource;

@Controller
public class AdminController {

    @Resource
    AdminService adminService;

    //获取用户账号和密码
    @RequestMapping(value = "/loginIn", method = RequestMethod.GET)
    @ResponseBody
    public ResultContent Admin(User u) {
        return adminService.admin(u);
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    @ResponseBody
    public ResultContent update(User u){
         return adminService.update(u);
    }
}
