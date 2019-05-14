package com.pq.pojo;

import java.util.List;

public class BpmTask {

    private String startName;//流程发起人名字
    private String taskId;//任务Id
    private String processInstanceId;//流程实例Id
    private Infor infor;//申请事项
    private String handleName;//处理人名字
    private Integer status;//判断流程是否完成
    private List<Procedure> procedureList;//已完成了的流程信息

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Infor getInfor() {
        return infor;
    }

    public void setInfor(Infor infor) {
        this.infor = infor;
    }

    public List<Procedure> getProcedureList() {
        return procedureList;
    }

    public void setProcedureList(List<Procedure> procedureList) {
        this.procedureList = procedureList;
    }

    public String getStartName() {
        return startName;
    }

    public void setStartName(String startName) {
        this.startName = startName;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public String getHandleName() {
        return handleName;
    }

    public void setHandleName(String handleName) {
        this.handleName = handleName;
    }
}
