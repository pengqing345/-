package com.pq.service;

import com.pq.pojo.Attend;
import com.pq.pojo.AttendRelation;
import com.pq.utils.ResultContent;

public interface AttendService {
    //插入用户和考勤的关系
    AttendRelation insertRelation(String userId);

    //插入考勤信息
    ResultContent insertAttend(String userId);

    //更新考勤信息
    ResultContent updateAttend(String userId);

    //查询所有考勤信息
    ResultContent selectAll();

    //根据userId查询具体的考勤信息
    ResultContent selectByUserId(String userId);

    //查找考勤信息
    Attend selectAttend(String userId);
}
