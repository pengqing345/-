package com.pq.pojo;

public class AttendRelation {

    private String userAttendId;
    private String userId;
    private String attendId;
    private String remark;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUserAttendId() {
        return userAttendId;
    }

    public void setUserAttendId(String userAttendId) {
        this.userAttendId = userAttendId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAttendId() {
        return attendId;
    }

    public void setAttendId(String attendId) {
        this.attendId = attendId;
    }
}
