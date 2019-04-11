package com.pq.controller;


import com.pq.pojo.Dept;
import com.pq.service.DeptService;
import com.pq.utils.ResultContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/dept")
public class DeptController {

    @Autowired
    private DeptService deptService;

    @RequestMapping("/info")
    @ResponseBody
    public ResultContent selectAll(){
        return  new ResultContent(0,"",deptService.selectAllDept());
    }

    @RequestMapping(value = "/del",method = RequestMethod.POST)
    @ResponseBody
    public ResultContent delByDeptId(String deptId){
        return  new ResultContent(0,"",deptService.delByDeptId(deptId));
    }

    @RequestMapping(value = "/insert",method = RequestMethod.GET )
    @ResponseBody
    public ResultContent insertDept(Dept dept){
        return  new ResultContent(0,"",deptService.insertDept(dept));
    }
}
