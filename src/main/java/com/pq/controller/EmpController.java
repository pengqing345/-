package com.pq.controller;


import com.pq.pojo.Emp;
import com.pq.service.EmpService;
import com.pq.utils.ResultContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/user")
public class EmpController {

    @Autowired
    private EmpService empService;

    @RequestMapping("/info")
    @ResponseBody
    public ResultContent selectByUserId(String userId){
        Emp emp = empService.selectByUserId(userId);
        return  new ResultContent(0,"",emp);
    }

    @RequestMapping("/allInfo")
    @ResponseBody
    public ResultContent selectAllEmp(){
        return new ResultContent(0,"",empService.selectAllEmp());
    }

}
