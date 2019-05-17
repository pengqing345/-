package com.pq.service.impl;

import com.pq.dao.UserMapper;
import com.pq.pojo.BPM;
import com.pq.pojo.BpmTask;
import com.pq.pojo.Infor;
import com.pq.pojo.Procedure;
import com.pq.service.BPMService;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class BPMServiceImpl implements BPMService {

    private ProcessEngine processEngine = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml").buildProcessEngine();
    private TaskService taskService = processEngine.getTaskService();
    private HistoryService historyService = processEngine.getHistoryService();

  @Autowired
  private UserMapper userMapper;

    //部署流程
    @Override
    public int startBPM(BPM bpm) {
        bpm.setAdminName("狗李");
        RepositoryService repositoryService = processEngine.getRepositoryService();
        IdentityService identityService = processEngine.getIdentityService();
        repositoryService.createDeployment().addClasspathResource("diagrams/apply.bpmn20.xml").deploy();
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
        Map<String, Object> variables = new HashMap<String, Object>();
        List<Task> list = processEngine.getTaskService()//
                .createTaskQuery()//
                .taskAssignee(HandleName)//个人任务的查询
                .list();
        if (list != null && list.size() > 0) {
            for (Task task : list) {
                //任务发起人名字
                String startUserId = historyService.createHistoricProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult().getStartUserId();
                if (startName.equals(startUserId)) {
                    variables.put("message", "ok");
                    taskService.complete(task.getId(),variables);
                    ProcessInstance nowPi = processEngine.getRuntimeService()
                            .createProcessInstanceQuery()
                            .processInstanceId(task.getProcessInstanceId())
                            .singleResult();
                    if(nowPi == null){
                        System.out.println("流程结束");
  /*                      Integer status = (Integer)taskService.getVariable(task.getId(),"status");
                        status = 1 ;
                        taskService.setVariable(task.getId(),"status",status);*/
                        userMapper.updateStatues(startUserId);
                    }
                }
            }
        }
        return 0;
    }

    //拒绝申请
    @Override
    public int refuseBPM(String HandleName, String startName) {
        Map<String, Object> variables = new HashMap<String, Object>();
        List<Task> list = processEngine.getTaskService()//
                .createTaskQuery()//
                .taskAssignee(HandleName)//个人任务的查询
                .list();
        if (list != null && list.size() > 0) {
            for (Task task : list) {
                if (historyService.createHistoricProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult().getStartUserId().equals(startName)) {
                    variables.put("message", "error");
                    taskService.complete(task.getId(), variables);
                }
            }
        }
        return 0;
    }

    //个人任务的查询
    public List<BpmTask> selectBpm(String name) {
        List<BpmTask> bpmTaskList = new ArrayList<>();
        //任务办理人
        List<Task> list = processEngine.getTaskService()//
                .createTaskQuery()//
                .taskAssignee(name)//个人任务的查询
                .list();
        if (list != null && list.size() > 0) {
            for (Task task : list) {
                BpmTask bpmTask = new BpmTask();
                String id = task.getId();
                Infor infor = (Infor) taskService.getVariable(id, "info");
                String processInstanceId = (String) taskService.getVariable(id, "processInstanceId");
                Integer status = (Integer) taskService.getVariable(id, "status");
                List<Procedure> procedureList = queryHistoricActivitiInstance(task.getProcessInstanceId());
                String startUserId = historyService.createHistoricProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult().getStartUserId();//获取发起人
                bpmTask.setStartName(startUserId);
                bpmTask.setProcessInstanceId(processInstanceId);
                bpmTask.setTaskId(id);
                bpmTask.setHandleName(name);
                bpmTask.setStatus(status);
                bpmTask.setProcedureList(procedureList);
                bpmTask.setInfor(infor);
                bpmTaskList.add(bpmTask);
            }
        }
        return bpmTaskList;

    }
    //跟踪查询
    @Override
    public List<BpmTask> selectBpmTask(String startName) {
        List<BpmTask> bpmTaskList = new ArrayList<>();
        List<ProcessDefinition> list = processEngine.getRepositoryService()
                .createProcessDefinitionQuery()
                .orderByProcessDefinitionVersion().desc()
                .list();//得到所有的流程
        if(list != null && list.size() > 0){
            for (ProcessDefinition p:list) {
                BpmTask bpmTask = new BpmTask();
                ProcessInstance nowPi = processEngine.getRuntimeService()
                        .createProcessInstanceQuery()
                        .processDefinitionId(p.getId())
                        .singleResult();
                if (nowPi == null) {
                    List<HistoricTaskInstance> list1 = processEngine.getHistoryService()
                            .createHistoricTaskInstanceQuery()
                            .processDefinitionId(p.getId()).list();
                    if (list1 != null && list1.size() > 0) {
                        for (HistoricTaskInstance hti : list1) {
                            System.out.println(hti.getId() + "    " + hti.getName() + "   " + hti.getClaimTime());
                            List<HistoricVariableInstance> list2 = processEngine.getHistoryService()
                                    .createHistoricVariableInstanceQuery()//创建一个历史的流程变量查询
                                    .processInstanceId(hti.getProcessInstanceId())
                                    .list();
                            Infor infor = null;
                            if (list != null && list.size() > 0) {
                                for (HistoricVariableInstance hiv : list2) {
                                     if(hiv.getVariableName().equals("info")){
                                         infor = (Infor)hiv.getValue();
                                     }
                                }
                            }
                            List<Procedure> procedureList = queryHistoricActivitiInstance2(hti.getProcessDefinitionId());
                            for (Procedure procedure : procedureList) {
                                if (procedure.getActivityName().equals("离职申请") && procedure.getAssignee().equals(startName)) {
                                    bpmTask.setProcessInstanceId(hti.getId());
                                    bpmTask.setStartName(startName);
                                    bpmTask.setStatus(1);
                                    bpmTask.setInfor(infor);
                                    break;
                                }
                            }
                        }
                    }

                }
                if (nowPi != null) {
                    List<Task> tasks = taskService.createTaskQuery().processDefinitionId(p.getId()).list();
                    for (Task task:tasks) {
                        Task singleResult = taskService.createTaskQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
                        Infor infor = (Infor) taskService.getVariable(singleResult.getId(), "info");
                        List<Procedure> procedureList = queryHistoricActivitiInstance(nowPi.getProcessInstanceId());
                        for (Procedure procedure : procedureList) {
                            if (procedure.getActivityName().equals("离职申请") && procedure.getAssignee().equals(startName)) {
                                bpmTask.setProcessInstanceId(nowPi.getProcessInstanceId());
                                bpmTask.setStartName(startName);
                                bpmTask.setInfor(infor);
                                bpmTask.setStatus(0);
                                break;
                            }
                    }
                    }
                }
                if(bpmTask.getProcessInstanceId() != null&&!bpmTask.getProcessInstanceId().equals("0")){
                    List<Procedure> procedureList = queryHistoricActivitiInstance(bpmTask.getProcessInstanceId());
                    bpmTask.setProcedureList(procedureList);
                }
                if(bpmTask.getStartName() != null){
                    bpmTaskList.add(bpmTask);
                }

            }

        }
         return bpmTaskList;
    }

    //查询流程经历了多少任务
    private List<Procedure> queryHistoricActivitiInstance(String processInstanceId){
        List<Procedure> procedureList = new ArrayList<>();
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
    //查询历史活动任务流程
    private List<Procedure> queryHistoricActivitiInstance2(String processDefinitionId){
        List<Procedure> procedureList = new ArrayList<>();
        //已进行了的流程信息
        List<HistoricActivityInstance> list = historyService.createHistoricActivityInstanceQuery().processDefinitionId(processDefinitionId).list();
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
                if (taskService.getVariable(id, "info") == null || taskService.getVariable(id, "info").equals("")) {
                    taskService.setVariable(id, "info", bpm.getInfor());
                }
                if (taskService.getVariable(id, "status") == null || taskService.getVariable(id, "status").equals("")) {
                    taskService.setVariable(id, "status", 0);
                }
                taskService.complete(id);
                //String processInstanceId = (String)taskService.getVariable(id, "processInstanceId");
                // String startUserId = historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult().getStartUserId();//获取发起人
            }
        }
    }
}
