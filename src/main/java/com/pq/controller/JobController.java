package com.pq.controller;

import com.pq.pojo.Job;
import com.pq.service.JobService;
import com.pq.utils.ResultContent;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/job")
public class JobController {
    @Autowired
    private JobService jobService;

    @ApiOperation(value = "显示所有的部门信息", notes = "请求方式：GET" + "JAVA类：com.pq.service.deptService "
            + "函数签名 ： ResultContent selectAll()")
    @RequestMapping(value = "/all",method = RequestMethod.GET)
    @ResponseBody
    public ResultContent selectAll(){
        List<Job> jobs = jobService.selectAllJob();
        return  new ResultContent(0,"success",jobs);
    }


}
