package com.pq.controller;

import com.pq.pojo.BPM;
import com.pq.service.BPMService;
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
@RequestMapping("/bpm")
public class BPMController {
    @Autowired
    private BPMService bpmService;

    @ApiOperation(value = "部署流程", notes = "请求方式：GET" + "JAVA类：com.pq.service.bpmService "
            + "函数签名 ： ResultContent startBPM( " + "BPM bpm);")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(paramType = "query", name = "申请人名字", value = "empName", required = true, dataType = "String"),
                    @ApiImplicitParam(paramType = "query", name = "部门审批人名字", value = "deptName", required = true, dataType = "String"),
                    @ApiImplicitParam(paramType = "query", name = "公司审批人名字", value = "adminName", required = true, dataType = "String"),
                    @ApiImplicitParam(paramType = "query", name = "原因", value = "describtion", required = false, dataType = "String")
            }
    )
    @RequestMapping(value = "/start", method = RequestMethod.GET)
    @ResponseBody
    public ResultContent startBPM(BPM bpm) {
        return new ResultContent(0, "", bpmService.startBPM(bpm));
    }

    @ApiOperation(value = "同意", notes = "请求方式：GET" + "JAVA类：com.pq.service.bpmService "
            + "函数签名 ： ResultContent completeBPM( " + "String HandleName,String startName);")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(paramType = "query", name = "申请人名字", value = "HandleName", required = true, dataType = "String"),
                    @ApiImplicitParam(paramType = "query", name = "部门审批人名字", value = "startName", required = true, dataType = "String")
            }
    )
    @RequestMapping(value = "/complete", method = RequestMethod.GET)
    @ResponseBody
    public ResultContent completeBPM(String HandleName, String startName) {
        return new ResultContent(0, "", bpmService.completeBPM(HandleName, startName));
    }

    @ApiOperation(value = "拒绝", notes = "请求方式：GET" + "JAVA类：com.pq.service.bpmService "
            + "函数签名 ：ResultContent refuseBPM( " + "String name);")
    @ApiImplicitParams(
                    @ApiImplicitParam(paramType = "query", name = "处理人名字", value = "name", required = true, dataType = "String")
    )
    @RequestMapping(value = "/refuse", method = RequestMethod.GET)
    @ResponseBody
    public ResultContent refuseBPM(String name) {
        return new ResultContent(0, "", bpmService.refuseBPM(name));
    }

    @ApiOperation(value = "查询当前任务", notes = "请求方式：GET" + "JAVA类：com.pq.service.bpmService "
            + "函数签名 ：ResultContent selectBpm( " + "String name);")
    @ApiImplicitParams(
            @ApiImplicitParam(paramType = "query", name = "处理人名字", value = "name", required = true, dataType = "String")
    )
    @RequestMapping(value = "/select", method = RequestMethod.GET)
    @ResponseBody
    public ResultContent selectBpm(String name) {
        return new ResultContent(0, "", bpmService.selectBpm(name));
    }

}
