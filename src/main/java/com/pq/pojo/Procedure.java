package com.pq.pojo;

/*
 * 流程步骤信息储存类
 */
public class Procedure {

    private String pId;//流程id
    private String activityId;//步骤ID
    private String activityName;//步骤名称
    private String assignee;//执行人

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }
}
