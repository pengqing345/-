package com.pq.service.impl;

import com.pq.dao.JobMapper;
import com.pq.pojo.Job;
import com.pq.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class JobServiceImpl implements JobService {

    @Autowired
    private JobMapper jobMapper;

    /*
    * 查询所有的部门信息
    */
    @Override
    public List<Job> selectAllJob() {
        List<Job> jobs = jobMapper.selectAllJob();
        for (Job job:jobs) {
            job.setCount(count(job.getJobId()));
        }
        return jobs;
    }
    /*查询每个部门下的总人数*/
    @Override
    public Integer count(String jobId) {
        return jobMapper.count(jobId);
    }
}
