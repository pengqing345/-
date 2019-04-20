package com.pq.pojo;

import java.util.Date;

public class Attend {
    private String attendId;
    private String attendDate;
    private String attentMorning;
    private String attentEvening;
    private Integer workHours;

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
