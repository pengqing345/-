package com.pq.controller;

import com.pq.pojo.Job;
import com.pq.service.JobService;
import com.pq.utils.ResultContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/job")
public class JobController {
    @Autowired
    private JobService jobService;

    @RequestMapping("/all")
    @ResponseBody
    public ResultContent selectAll(){
        List<Job> jobs = jobService.selectAllJob();
        return  new ResultContent(0,"success",jobs);
    }


}
