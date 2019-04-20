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

    @RequestMapping("/up")
    @ResponseBody
    public ResultContent addAttend(String userId){
         if(userId != null && !userId.equals("")){
             attendService.insertRelation(userId);
             ResultContent resultContent = attendService.insertAttend(userId);
             return resultContent;
         }
        return new ResultContent(-1,"userId不能为空",null);
    }

    @RequestMapping("/down")
    @ResponseBody
    public ResultContent updateAttend(String userId){
        if(userId != null && !userId.equals("")){
            ResultContent resultContent = attendService.updateAttend(userId);
            return resultContent;
        }
        return new ResultContent(-1,"userId不能为空",null);
    }

    @RequestMapping("/all")
    @ResponseBody
    public ResultContent selectAll(){
        return attendService.selectAll();
    }

}
