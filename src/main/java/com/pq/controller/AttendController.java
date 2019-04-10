package com.pq.controller;

import com.pq.pojo.Attend;
import com.pq.service.AttendService;
import com.pq.utils.ResultContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/attend")
public class AttendController {

    @Autowired
    private AttendService attendService;

    @RequestMapping("/add")
    @ResponseBody
    public ResultContent addAttend(Attend attend , String userId){
         if(userId != null && !userId.equals("")){
             attendService.insertRelation(userId);
         }
        ResultContent resultContent = attendService.insertAttend(attend, userId);
         return resultContent;
    }

}
