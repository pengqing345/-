package com.pq.service.impl;

import com.pq.pojo.BPM;
import com.pq.pojo.BpmTask;
import com.pq.pojo.Procedure;
import com.pq.service.BPMService;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.task.Task;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class BPMServiceImpl implements BPMService {

    private ProcessEngine processEngine = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml").buildProcessEngine();
    private TaskService taskService = processEngine.getTaskService();
    private HistoryService historyService = processEngine.getHistoryService();

    //部署流程
    @Override
    public int startBPM(BPM bpm) {
        RepositoryService repositoryService = processEngine.getRepositoryService();
        IdentityService identityService = processEngine.getIdentityService();
        repositoryService.createDeployment().addClasspathResource("diagram/apply.bpmn20.xml").deploy();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("empName", bpm.getEmpName());
        map.put("deptName", bpm.getDeptName());
        map.put("adminName", bpm.getAdminName());
        identityService.setAuthenticatedUserId(bpm.getEmpName());
        processEngine.getRuntimeService().startProcessInstanceByKey("apply", map);
        selectBpmByName(bpm);
        return 0;
    }

    //完成任务
    @Override
    public int completeBPM(String HandleName, String startName) {
        List<Task> list = processEngine.getTaskService()//
                .createTaskQuery()//
                .taskAssignee(HandleName)//个人任务的查询
                .list();
        if (list != null && list.size() > 0) {
            for (Task task : list) {
                //任务发起人名字
                String startUserId = historyService.createHistoricProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult().getStartUserId();
                if (startName.equals(startUserId)) {
                    taskService.complete(task.getId());
                }
            }
        }
        return 0;
    }

    //拒绝申请
    @Override
    public int refuseBPM(String name) {
        Map<String, Object> variables = new HashMap<String, Object>();
        List<Task> list = processEngine.getTaskService()//
                .createTaskQuery()//
                .taskAssignee(name)//个人任务的查询
                .list();
        if (list != null && list.size() > 0) {
            for (Task task : list) {
                if (!historyService.createHistoricProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult().getStartUserId().equals(name)) {
                    variables.put("message", "error");
                    taskService.complete(task.getId(), variables);
                }
            }
        }
        return 0;
    }

    //个人任务的查询
    public List<BpmTask> selectBpm(String name) {
        List<BpmTask> bpmTaskList = null;
        //任务办理人
        List<Task> list = processEngine.getTaskService()//
                .createTaskQuery()//
                .taskAssignee(name)//个人任务的查询
                .list();
        if (list != null && list.size() > 0) {
            for (Task task : list) {
                List<Procedure> procedureList = queryHistoricActivitiInstance(task.getProcessInstanceId());
                BpmTask bpmTask = new BpmTask();
                String id = task.getId();
                String describtion = (String) taskService.getVariable(id, "describtion");
                String processInstanceId = (String) taskService.getVariable(id, "processInstanceId");
                String startUserId = historyService.createHistoricProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult().getStartUserId();//获取发起人
                bpmTask.setStartName(startUserId);
                bpmTask.setProcessInstanceId(processInstanceId);
                bpmTask.setTaskId(id);
                bpmTask.setHandleName(name);
                bpmTask.setProcedureList(procedureList);
                bpmTask.setDescribtion(describtion);
                bpmTaskList.add(bpmTask);
            }
        }
        return bpmTaskList;

    }

    //查询流程经历了多少任务
    private List<Procedure> queryHistoricActivitiInstance(String processInstanceId){
        List<Procedure> procedureList = null;
        //已进行了的流程信息
        List<HistoricActivityInstance> list = historyService.createHistoricActivityInstanceQuery().processInstanceId(processInstanceId).list();
        if (list != null && list.size() > 0) {
            for (HistoricActivityInstance hai : list) {
                Procedure procedure = new Procedure();
                procedure.setpId(hai.getId());//任务Id
                procedure.setActivityId(hai.getActivityId());//步骤ID
                procedure.setActivityName(hai.getActivityName());//步骤名称
                procedure.setAssignee(hai.getAssignee());//执行人
                procedureList.add(procedure);
            }
        }
        return procedureList;
    }
    //查询某角色的任务id
    private void selectBpmByName(BPM bpm) {
        //任务办理人
        List<Task> list = processEngine.getTaskService()//
                .createTaskQuery()//
                .taskAssignee(bpm.getEmpName())//个人任务的查询
                .list();
        if (list != null && list.size() > 0) {
            for (Task task : list) {
                String processInstanceId = task.getProcessInstanceId();
                String id = task.getId();
                if (taskService.getVariable(id, "processInstanceId") == null || taskService.getVariable(id, "processInstanceId").equals("")) {
                    taskService.setVariable(id, "processInstanceId", processInstanceId);
                }
                if (taskService.getVariable(id, "describtion") == null || taskService.getVariable(id, "describtion").equals("")) {
                    taskService.setVariable(id, "describtion", bpm.getDescribtion());
                }
                //String processInstanceId = (String)taskService.getVariable(id, "processInstanceId");
                // String startUserId = historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult().getStartUserId();//获取发起人
            }
        }
    }
}
