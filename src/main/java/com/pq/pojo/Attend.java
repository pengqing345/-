package com.pq.pojo;

import java.util.Date;

public class Attend {
    private String attendId;
    private String attendDate;
    private Date attentMorning;
    private Date attentEvening;
    private Integer workHours;
    private Integer absence;//缺勤时长
    private Integer attendStatus;//考勤状态

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

    public Date getAttentMorning() {
        return attentMorning;
    }

    public void setAttentMorning(Date attentMorning) {
        this.attentMorning = attentMorning;
    }

    public Date getAttentEvening() {
        return attentEvening;
    }

    public void setAttentEvening(Date attentEvening) {
        this.attentEvening = attentEvening;
    }

    public Integer getWorkHours() {
        return workHours;
    }

    public void setWorkHours(Integer workHours) {
        this.workHours = workHours;
    }

    public Integer getAbsence() {
        return absence;
    }

    public void setAbsence(Integer absence) {
        this.absence = absence;
    }

    public Integer getAttendStatus() {
        return attendStatus;
    }

    public void setAttendStatus(Integer attendStatus) {
        this.attendStatus = attendStatus;
    }
}
