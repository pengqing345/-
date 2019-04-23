package com.pq.dao;

import com.pq.pojo.Attend;
import com.pq.pojo.AttendRelation;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendMapper {

    int insertRelation(AttendRelation attendRelation);

    int insertAttend(Attend attend);

    List<String> selectRelationByUserId(@Param("userId") String userId);

    int updateAttend(Attend attend);

    Attend selectByAttendId(@Param("attendId") String attendId);

    String selectForDeptName(@Param("userId") String userId);

    String selectForJobName(@Param("userId") String userId);

    List<Attend> selectAll();

   Integer countForWork(@Param("userId") String userId ,@Param("startTime") String startTime, @Param("endTime") String endTime);

    String selectForEmpName(@Param("userId")String userId);

    int selectCountByUserId(@Param("userId") String userId , @Param("date")String date);
}
