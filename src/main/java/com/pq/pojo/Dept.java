package com.pq.pojo;

import java.util.List;

public class Dept {
    private String deptId;
    private String deptName;
    private String deptRemark;
    private Integer count;
    private List<String> jobName;

    public List<String> getJobName() {
        return jobName;
    }

    public void setJobName(List<String> jobName) {
        this.jobName = jobName;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptRemark() {
        return deptRemark;
    }

    public void setDeptRemark(String deptRemark) {
        this.deptRemark = deptRemark;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
