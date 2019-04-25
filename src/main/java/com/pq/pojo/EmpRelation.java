package com.pq.pojo;

public class EmpRelation {
    private String empParamId;
    private String empId;
    private String deptId;
    private String jobId;
    private Integer status;
    private String remark;

    public String getEmpParamId() {
        return empParamId;
    }

    public void setEmpParamId(String empParamId) {
        this.empParamId = empParamId;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
