package com.pq.controller;


import com.pq.service.PowerService;
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
@RequestMapping("/power")
public class PowerController {

    @Autowired
    private PowerService powerService;

    @ApiOperation(value = "查询所有角色", notes = "请求方式：GET" + "JAVA类：com.pq.service.powerService "
            + "函数签名 ： ResultContent selectAll()（ " + "String userId);")

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public ResultContent selectAll() {
        return powerService.selectAll();
    }

    @ApiOperation(value = "插入角色权限", notes = "请求方式：GET" + "JAVA类：com.pq.service.powerService "
            + "函数签名 ： ResultContent insertRelation（ " + "String roleId, String userId);")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(paramType = "query", name = "roleId", value = "roleId", required = true, dataType = "String"),
                    @ApiImplicitParam(paramType = "query", name = "userId", value = "userId", required = true, dataType = "String")
            }
    )
    @RequestMapping(value = "/insert", method = RequestMethod.GET)
    @ResponseBody
    public ResultContent insertRelation(String roleId, String userId) {
        return powerService.insertUserRelation(roleId, userId);
    }
}
