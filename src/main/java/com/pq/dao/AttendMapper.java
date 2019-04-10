package com.pq.dao;

import com.pq.pojo.Attend;
import com.pq.pojo.AttendRelation;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendMapper {

    int insertRelation(AttendRelation attendRelation);

    int insertAttend(Attend attend);

    String selectRelationByUserId(@Param("userId") String userId);
}
