package com.pq.service;

import com.pq.pojo.Job;

import java.util.List;

public interface JobService {
    List<Job> selectAllJob();
    Integer count(String jobId);
}
