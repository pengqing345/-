package com.pq.controller;


import com.pq.service.AttendService;
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
@RequestMapping("/attend")
public class AttendController {

    @Autowired
    private AttendService attendService;

    @ApiOperation(value = "上班打卡", notes = "请求方式：GET" + "JAVA类：com.pq.service.attendService "
            + "函数签名 ： ResultContent updateAttend（ " + "String userId);")
    @ApiImplicitParams(
            @ApiImplicitParam(paramType = "query", name = "userId", value = "userId", required = true, dataType = "String")
    )
    @RequestMapping(value = "/up",method = RequestMethod.GET)
    @ResponseBody
    public ResultContent addAttend(String userId) {
        if (userId != null && !userId.equals("")) {
            Integer relation = attendService.insertRelation(userId);
            if(relation != 1){
                return new ResultContent(-1, "插入关系失败", "");
            }
            ResultContent resultContent = attendService.insertAttend(userId);
            return resultContent;
        }
        return new ResultContent(-1, "userId不能为空", "");
    }

    @ApiOperation(value = "下班打卡", notes = "请求方式：GET" + "JAVA类：com.pq.service.attendService "
            + "函数签名 ： ResultContent updateAttend（ " + "String userId);")
    @ApiImplicitParams(
            @ApiImplicitParam(paramType = "query", name = "userId", value = "userId", required = true, dataType = "String")
    )

    @RequestMapping(value = "/down",method = RequestMethod.GET)
    @ResponseBody
    public ResultContent updateAttend(String userId) {
        if (userId != null && !userId.equals("")) {
            ResultContent resultContent = attendService.updateAttend(userId);
            return resultContent;
        }
        return new ResultContent(-1, "userId不能为空", null);
    }
    @ApiOperation(value = "查询所有考勤信息", notes = "请求方式：GET" + "JAVA类：com.pq.service.attendService "
            + "函数签名 ： ResultContent selectAll")
    @RequestMapping(value = "/all",method = RequestMethod.GET)
    @ResponseBody
    public ResultContent selectAll() {
        return attendService.selectAll();
    }

    @ApiOperation(value = "显示个人考勤情况", notes = "请求方式：GET" + "JAVA类：com.pq.service.attendService "
            + "函数签名 ： ResultContent selectByUserId（ " + "String userId);")
    @ApiImplicitParams(
            @ApiImplicitParam(paramType = "query", name = "userId", value = "userId", required = true, dataType = "String")
    )
    @RequestMapping(value = "/info",method = RequestMethod.GET)
    @ResponseBody
    public ResultContent selectByUserId(String userId) {
        return attendService.selectByUserId(userId);
    }

}
