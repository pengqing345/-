package com.pq.controller;

import com.pq.pojo.BPM;
import com.pq.pojo.Infor;
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
                    @ApiImplicitParam(paramType = "query", name = "empName", value = "申请人名字", required = true, dataType = "String"),
                    @ApiImplicitParam(paramType = "query", name = "deptName", value = "部门审批人名字", required = true, dataType = "String"),
                    @ApiImplicitParam(paramType = "query", name = "empId", value = "用户ID", required = false, dataType = "String"),
                    @ApiImplicitParam(paramType = "query", name = "dept", value = "部门名称", required = false, dataType = "String"),
                    @ApiImplicitParam(paramType = "query", name = "in", value = "申请时间", required = false, dataType = "String"),
                    @ApiImplicitParam(paramType = "query", name = "title", value = "申请标题", required = false, dataType = "String"),
                    @ApiImplicitParam(paramType = "query", name = "detailres", value = "detailres", required = false, dataType = "String"),
                    @ApiImplicitParam(paramType = "query", name = "describtion", value = "描述", required = false, dataType = "String"),
                    @ApiImplicitParam(paramType = "query", name = "leave", value = "离职时间", required = false, dataType = "String")

            }
    )
    @RequestMapping(value = "/start", method = RequestMethod.GET)
    @ResponseBody
    public ResultContent startBPM(BPM bpm, Infor infor) {
        infor.setIn(infor.getIn().split("G")[0]);
        infor.setLeave(infor.getLeave().split("G")[0]);
        bpm.setInfor(infor);
        return new ResultContent(0, "", bpmService.startBPM(bpm));
    }

    @ApiOperation(value = "同意", notes = "请求方式：GET" + "JAVA类：com.pq.service.bpmService "
            + "函数签名 ： ResultContent completeBPM( " + "String HandleName,String startName);")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(paramType = "query", name = "HandleName", value = "申请人名字", required = true, dataType = "String"),
                    @ApiImplicitParam(paramType = "query", name = "startName", value = "发起人名字", required = true, dataType = "String")
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
            {
                    @ApiImplicitParam(paramType = "query", name = "handleName", value = "处理人名字", required = true, dataType = "String"),
                    @ApiImplicitParam(paramType = "query", name = "startName", value = "开始人名字", required = true, dataType = "String")
            }
    )
    @RequestMapping(value = "/refuse", method = RequestMethod.GET)
    @ResponseBody
    public ResultContent refuseBPM(String handleName, String startName) {
        return new ResultContent(0, "", bpmService.refuseBPM(handleName,startName));
    }

    @ApiOperation(value = "查询当前任务", notes = "请求方式：GET" + "JAVA类：com.pq.service.bpmService "
            + "函数签名 ：ResultContent selectBpm( " + "String name);")
    @ApiImplicitParams(
            @ApiImplicitParam(paramType = "query", name = "name", value = "处理人名字", required = true, dataType = "String")
    )
    @RequestMapping(value = "/select", method = RequestMethod.GET)
    @ResponseBody
    public ResultContent selectBpm(String name) {
        return new ResultContent(0, "", bpmService.selectBpm(name));
    }

    @ApiOperation(value = "任务跟踪", notes = "请求方式：GET" + "JAVA类：com.pq.service.bpmService "
            + "函数签名 ：ResultContent selectBpm( " + "String name);")
    @ApiImplicitParams(
            @ApiImplicitParam(paramType = "query", name = "name", value = "处理人名字", required = true, dataType = "String")
    )
    @RequestMapping(value = "/selectBpmTask", method = RequestMethod.GET)
    @ResponseBody
    public ResultContent selectBpmTask(String name) {
        return new ResultContent(0, "", bpmService.selectBpmTask(name));
    }



}
