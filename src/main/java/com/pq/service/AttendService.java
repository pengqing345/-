package com.pq.service;

import com.pq.utils.ResultContent;

public interface AttendService {
    //插入用户和考勤的关系
    ResultContent insertRelation(String userId);
    //插入考勤信息
    ResultContent insertAttend(String userId);
    //更新考勤信息
    ResultContent updateAttend(String userId);

    ResultContent selectAll();
}
