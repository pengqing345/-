package com.pq.pojo;

import java.io.Serializable;
import java.util.Date;

public class Infor implements Serializable {

    private static final long serialVersionUID = 8379071759772449529L;

    private String empId;
    private String dept;
    private String in;
    private String title;
    private String leave;
    private String detailres;
    private String describtion;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getDept() {
        return dept;
    }

    public void setDeptName(String dept) {
        this.dept = dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getIn() {
        return in;
    }

    public void setIn(String in) {
        this.in = in;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLeave() {
        return leave;
    }

    public void setLeave(String leave) {
        this.leave = leave;
    }

    public String getDetailres() {
        return detailres;
    }

    public void setDetailres(String detailres) {
        this.detailres = detailres;
    }

    public String getDescribtion() {
        return describtion;
    }

    public void setDescribtion(String describtion) {
        this.describtion = describtion;
    }
}
