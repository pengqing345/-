package com.pq.controller;


import com.pq.pojo.Emp;
import com.pq.service.EmpService;
import com.pq.utils.ResultContent;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/user")
public class EmpController {

    @Autowired
    private EmpService empService;

    @ApiOperation(value = "显示用户个人信息", notes = "请求方式：GET" + "JAVA类：com.pq.service.empService "
            + "函数签名 ：ResultContent selectByUserId（ " + "String userId);")
    @ApiImplicitParams(
            @ApiImplicitParam(paramType = "query", name = "userId", value = "userId", required = true, dataType = "String")
    )
    @RequestMapping(value = "/info",method = RequestMethod.GET)
    @ResponseBody
    public ResultContent selectByUserId(String userId){
        Emp emp = empService.selectByUserId(userId);
        return  new ResultContent(0,"",emp);
    }

    @ApiOperation(value = "显示所有用户信息", notes = "请求方式：GET" + "JAVA类：com.pq.service.empService "
            + "函数签名 ：ResultContent selectByUserId")
    @RequestMapping(value = "/allInfo",method = RequestMethod.GET)
    @ResponseBody
    public ResultContent selectAllEmp(){
        return new ResultContent(0,"",empService.selectAllEmp());
    }

    @ApiOperation(value = "删除个人信息", notes = "请求方式：POST" + "JAVA类：com.pq.service.empService "
            + "函数签名 ：ResultContent selectByUserId（ " + "String userId);")
    @ApiImplicitParams(
            @ApiImplicitParam(paramType = "query", name = "empId", value = "empId", required = true, dataType = "String")
    )
    @RequestMapping(value = "/del",method = RequestMethod.POST)
    @ResponseBody
    public ResultContent delEmp(String empId){
       return  new ResultContent(0,"",empService.delEmp(empId));
    }
}
