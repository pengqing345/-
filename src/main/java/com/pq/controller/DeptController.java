package com.pq.controller;


import com.pq.pojo.Dept;
import com.pq.service.DeptService;
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
@RequestMapping(value = "/dept")
public class DeptController {

    @Autowired
    private DeptService deptService;

    @ApiOperation(value = "显示所有的部门信息", notes = "请求方式：GET" + "JAVA类：com.pq.service.deptService "
            + "函数签名 ： ResultContent selectAll()")
    @RequestMapping(value = "/info",method = RequestMethod.GET)
    @ResponseBody
    public ResultContent selectAll() {
        return new ResultContent(0, "", deptService.selectAllDept());
    }

    @ApiOperation(value = "删除部门信息", notes = "请求方式：POST" + "JAVA类：com.pq.service.deptService "
            + "函数签名 ：ResultContent delByDeptId( " + "String deptId);")
    @ApiImplicitParams(
            @ApiImplicitParam(paramType = "query", name = "deptId", value = "deptId", required = true, dataType = "String")
    )
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ResponseBody
    public ResultContent delByDeptId(String deptId) {
        return new ResultContent(0, "", deptService.delByDeptId(deptId));
    }

    @ApiOperation(value = "新增部门", notes = "请求方式：GET" + "JAVA类：com.pq.service.deptService "
            + "函数签名 ：ResultContent insertDept( " + "Dept dept);")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(paramType = "query", name = "deptId", value = "deptId", required = false, dataType = "String"),
                    @ApiImplicitParam(paramType = "query", name = "部门名称", value = "deptName", required = true, dataType = "String"),
                    @ApiImplicitParam(paramType = "query", name = "部门介绍", value = "deptRemark", required = false, dataType = "String")
            }
    )
    @RequestMapping(value = "/insert", method = RequestMethod.GET)
    @ResponseBody
    public ResultContent insertDept(Dept dept) {
        if(dept.getDeptId() != null ){
            return new ResultContent(0, "",deptService.updateByDeptId(dept));
        }
        return new ResultContent(0, "",deptService.insertDept(dept));
    }
}
