package com.pq.service.impl;

import com.pq.dao.AttendMapper;
import com.pq.dao.UserMapper;
import com.pq.pojo.AllAttend;
import com.pq.pojo.Attend;
import com.pq.pojo.AttendRelation;
import com.pq.pojo.Emp;
import com.pq.service.AttendService;
import com.pq.utils.DateUtils;
import com.pq.utils.GetRandon;
import com.pq.utils.ResultContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class AttendServiceImpl implements AttendService {

    @Autowired
    private AttendMapper attendMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public ResultContent insertRelation(String userId) {
        AttendRelation attendRelation = new AttendRelation();
        attendRelation.setUserId(userId);
        String attendId = GetRandon.getRandom(16);
        attendRelation.setAttendId(attendId);
        String userAttendId = GetRandon.getRandom(16);
        attendRelation.setUserAttendId(userAttendId);
        String empName = attendMapper.selectForEmpName(userId);
        String fillDate = DateUtils.getFillDate(new Date(System.currentTimeMillis()));
        attendRelation.setRemark(empName+"-"+fillDate.split("-")[1] +"+打卡记录");
        int i = attendMapper.insertRelation(attendRelation);
        return new ResultContent(0, "", i);
    }

    /*
     *上班打卡
     */
    @Override
    public ResultContent insertAttend(String userId) {
        Attend attend = new Attend();
        String attendId = attendMapper.selectRelationByUserId(userId);
        attend.setAttendId(attendId);
        long timeMillis = System.currentTimeMillis();
        Date date = new Date(timeMillis);
        attend.setAttentMorning(DateUtils.getFillDateAndTime(date));
        attend.setAttendDate(DateUtils.getFillDate(date));
        int i = attendMapper.insertAttend(attend);
        return new ResultContent(0, "", i);
    }

    /*
     * 下班打卡
     */
    @Override
    public ResultContent updateAttend(String userId) {
        Attend attend = attendMapper.selectByAttendId(attendMapper.selectRelationByUserId(userId));
        long timeMillis = System.currentTimeMillis();
        Date date = new Date(timeMillis);
        attend.setAttentEvening(DateUtils.getFillDateAndTime(date));
        Long hours = DateUtils.getHours(attend.getAttentMorning().split(" ")[1], DateUtils.getTimes(date));
        attend.setWorkHours(Integer.parseInt(String.valueOf(hours)));
        int i = attendMapper.updateAttend(attend);
        return new ResultContent(0, "", i);
    }

    /*
     * 查询所有考勤情况
     */
    @Override
    public ResultContent selectAll() {
        List<AllAttend>  allAttends = new ArrayList<>();
        List<Emp> emps = userMapper.selectAllEmp();
        for (Emp emp:emps) {
            AllAttend allAttend = new AllAttend();
            String fillDate = DateUtils.getFillDate(new Date(System.currentTimeMillis()));
            String times =  fillDate.split("-")[1];
            int i = Integer.parseInt(times);
            Integer integer = attendMapper.countForWork(emp.getEmpId(), fillDate.split("-")[0] + "-" + (i - 1) + "-01", fillDate.split("-")[0] + "-" + i + "-01");
            String jobName = attendMapper.selectForJobName(emp.getEmpId());
            String deptName = attendMapper.selectForDeptName(emp.getEmpId());
            allAttend.setDeptName(deptName);
            allAttend.setJobName(jobName);
            allAttend.setUserId(emp.getEmpId());
            allAttend.setAttend(integer);
            if(integer < 23){
                allAttend.setAbsence(22-integer);
            }else{
                allAttend.setAbsence(23-integer);
            }
        }
        return  new ResultContent(0,"",allAttends);
    }
}
