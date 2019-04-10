package com.pq.service.impl;

import com.pq.dao.AttendMapper;
import com.pq.pojo.Attend;
import com.pq.pojo.AttendRelation;
import com.pq.service.AttendService;
import com.pq.utils.DateUtils;
import com.pq.utils.GetRandon;
import com.pq.utils.ResultContent;
import jdk.nashorn.internal.ir.IdentNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public ResultContent insertAttend(Attend attend,String userId) {
        String attendId = attendMapper.selectRelationByUserId(userId);
        attend.setAttendId(attendId);
        Long hours = DateUtils.getHours(DateUtils.getFillDate(attend.getAttentMorning()),DateUtils.getFillDate(attend.getAttentEvening()));
        Integer hour = Integer.parseInt(String.valueOf(hours));
        attend.setWorkHours(hour);
        Integer status = DateUtils.compareDate(DateUtils.getFillDate(attend.getAttentMorning()), "9：00");
        attend.setAttendStatus(status);
        int i = attendMapper.insertAttend(attend);
        return new ResultContent(0,"",i);
    }
}
