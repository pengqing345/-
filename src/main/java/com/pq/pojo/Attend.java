package com.pq.pojo;



public class Attend  {
    private String attendId;
    private String attendDate;
    private String attentMorning;
    private String attentEvening;
    private Integer workHours;
    private Integer upStatus;//上班打卡标志
    private Integer downStatus;//下班打卡标志
    private Integer absence;//缺勤天数
    private String deptName;
    private String jobName;
    private String userName;
    private String empName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public Integer getUpStatus() {
        return upStatus;
    }

    public void setUpStatus(Integer upStatus) {
        this.upStatus = upStatus;
    }

    public Integer getDownStatus() {
        return downStatus;
    }

    public void setDownStatus(Integer downStatus) {
        this.downStatus = downStatus;
    }

    public Integer getAbsence() {
        return absence;
    }

    public void setAbsence(Integer absence) {
        this.absence = absence;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getAttendId() {
        return attendId;
    }

    public void setAttendId(String attendId) {
        this.attendId = attendId;
    }

    public String getAttendDate() {
        return attendDate;
    }

    public void setAttendDate(String attendDate) {
        this.attendDate = attendDate;
    }

    public String getAttentMorning() {
        return attentMorning;
    }

    public void setAttentMorning(String attentMorning) {
        this.attentMorning = attentMorning;
    }

    public String getAttentEvening() {
        return attentEvening;
    }

    public void setAttentEvening(String attentEvening) {
        this.attentEvening = attentEvening;
    }

    public Integer getWorkHours() {
        return workHours;
    }

    public void setWorkHours(Integer workHours) {
        this.workHours = workHours;
    }

}
