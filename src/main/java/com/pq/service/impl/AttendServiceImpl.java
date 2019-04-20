package com.pq.service.impl;

import com.pq.dao.AttendMapper;
import com.pq.pojo.Attend;
import com.pq.pojo.AttendRelation;
import com.pq.service.AttendService;
import com.pq.utils.DateUtils;
import com.pq.utils.GetRandon;
import com.pq.utils.ResultContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class AttendServiceImpl implements AttendService {

    @Autowired
    private AttendMapper attendMapper;

    @Override
    public ResultContent insertRelation(String userId) {
        AttendRelation attendRelation = new AttendRelation();
        attendRelation.setUserId(userId);
        String attendId = GetRandon.getRandom(16);
        attendRelation.setAttendId(attendId);
        String userAttendId = GetRandon.getRandom(16);
        attendRelation.setUserAttendId(userAttendId);
        attendRelation.setRemark("admin-attend关系数据");
        int i = attendMapper.insertRelation(attendRelation);
        return new ResultContent(0,"",i);
    }

    @Override
    public ResultContent insertAttend(String userId) {
        Attend attend = new Attend();
        String attendId = attendMapper.selectRelationByUserId(userId);
        attend.setAttendId(attendId);
        long timeMillis = System.currentTimeMillis();
        Date date = new Date(timeMillis);
        attend.setAttentMorning(DateUtils.getFillDateAndTime(date));
        int i = attendMapper.insertAttend(attend);
        return new ResultContent(0,"",i);
    }

    @Override
    public ResultContent updateAttend(String userId) {
        Attend attend = attendMapper.selectByAttendId(attendMapper.selectRelationByUserId(userId));
        long timeMillis = System.currentTimeMillis();
        Date date = new Date(timeMillis);
        attend.setAttentEvening(DateUtils.getFillDateAndTime(date));
        Long hours = DateUtils.getHours(attend.getAttentMorning().split(" ")[1],DateUtils.getTimes(date));
        attend.setWorkHours(Integer.parseInt(String.valueOf(hours)));
        int i = attendMapper.updateAttend(attend);
        return new ResultContent(0,"",i);
    }
}
