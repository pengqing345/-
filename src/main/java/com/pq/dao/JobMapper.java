package com.pq.dao;

import com.pq.pojo.Job;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobMapper {
     List<Job> selectAllJob();
     Integer count(@Param("jobId")String jobId);
}
