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
import java.util.Map;

@Service
@Transactional
public class AttendServiceImpl implements AttendService {

    @Autowired
    private AttendMapper attendMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public AttendRelation insertRelation(String userId) {
        AttendRelation attendRelation = new AttendRelation();
        attendRelation.setUserId(userId);
        String attendId = "12" + GetRandon.getRandom(14);
        attendRelation.setAttendId(attendId);
        String userAttendId = "12"+ GetRandon.getRandom(14);
        attendRelation.setUserAttendId(userAttendId);
        String empName = attendMapper.selectForEmpName(userId);
        String fillDate = DateUtils.getFillDate(new Date(System.currentTimeMillis()));
        attendRelation.setRemark(empName + "-" + fillDate.split("-")[1] + "-打卡记录");
         attendMapper.insertRelation(attendRelation);
        return attendRelation;
    }

    /*
     *上班打卡
     */
    @Override
    public ResultContent insertAttend(String userId) {
        Attend attend = new Attend();
        List<String> attendId = attendMapper.selectRelationByUserId(userId,null, null);
        attend.setAttendId(attendId.get(attendId.size() - 1));
        long timeMillis = System.currentTimeMillis();
        Date date = new Date(timeMillis);
        attend.setAttentMorning(DateUtils.getTimes(date));
        attend.setAttendDate(DateUtils.getFillDate(date));
        attend.setUpStatus(0);
        int i = attendMapper.updateAttend(attend);
        return new ResultContent(0, "", i);
    }

    /*
     * 下班打卡
     */
    @Override
    public ResultContent updateAttend(String userId) {
        List<String> strings = attendMapper.selectRelationByUserId(userId,null,null);
        Attend attend = attendMapper.selectByAttendId(strings.get(strings.size() - 1));
        long timeMillis = System.currentTimeMillis();
        Date date = new Date(timeMillis);
        attend.setAttentEvening(DateUtils.getTimes(date));
        Long hours = DateUtils.getHours(attend.getAttentMorning(), DateUtils.getTimes(date));
        attend.setWorkHours(Integer.parseInt(String.valueOf(hours)));
        attend.setDownStatus(0);
        int i = attendMapper.updateAttend(attend);
        return new ResultContent(0, "", i);
    }

    /*
     * 查询所有考勤情况
     */
    @Override
    public ResultContent selectAll() {
        List<AllAttend> allAttends = new ArrayList<>();
        List<Emp> emps = userMapper.selectAllEmp();
        for (Emp emp : emps) {
            AllAttend allAttend = new AllAttend();
            String fillDate = DateUtils.getFillDate(new Date(System.currentTimeMillis()));
            String times = fillDate.split("-")[1];
            int i = Integer.parseInt(times);
            Integer integer = attendMapper.countForWork(emp.getEmpId(), fillDate.split("-")[0] + "-0" + (i - 1) + "-01", fillDate.split("-")[0] + "-0" + i + "-01");
            String jobName = attendMapper.selectForJobName(emp.getEmpId());
            String deptName = attendMapper.selectForDeptName(emp.getEmpId());
            String userName = userMapper.selectUserName(emp.getEmpId());
            allAttend.setDeptName(deptName);
            allAttend.setJobName(jobName);
            allAttend.setUserId(emp.getEmpId());
            allAttend.setEmpName(emp.getEmpName());
            allAttend.setUserName(userName);
            allAttend.setAttend(integer);
            if (integer < 23) {
                allAttend.setAbsence(22 - integer);
            } else {
                allAttend.setAbsence(23 - integer);
            }
            allAttends.add(allAttend);
        }
        return new ResultContent(0, "", allAttends);
    }

    /*
     * 查询个人考勤信息
     */
    @Override
    public ResultContent selectByUserId(String userId) {
        List<Attend> attends = new ArrayList<>();
        String fillDate = DateUtils.getFillDate(new Date(System.currentTimeMillis()));
        String times = fillDate.split("-")[1];
        int i = Integer.parseInt(times);
        String startTime = fillDate.split("-")[0] + "-0" + i + "-01";
        String endTime = fillDate.split("-")[0] + "-0" + (i + 1) + "-01";
        List<String> attendIds = attendMapper.selectRelationByUserId(userId,startTime,endTime);
        for (String attendId : attendIds) {
            Attend attend = attendMapper.selectByAttendId(attendId);
            attend.setJobName(attendMapper.selectForJobName(userId));
            attend.setDeptName(attendMapper.selectForDeptName(userId));
            attend.setEmpName(userMapper.selectByUserId(userId).getEmpName());
            attend.setUserName(userMapper.selectUserName(userId));
            attends.add(attend);
        }
        return new ResultContent(0, "", attends);
    }
     /*
     * 查询是否有今日考勤信息
     */
    @Override
    public Attend selectAttend(String userId) {
        String fillDate = DateUtils.getFillDate(new Date(System.currentTimeMillis()));
        int i = attendMapper.selectCountByUserId(userId, fillDate);
        if(i == 0){
            AttendRelation attendRelation = insertRelation(userId);
            Attend attend = new Attend();
            attend.setAttendId(attendRelation.getAttendId());
            attend.setUpStatus(1);
            attend.setDownStatus(1);
            attendMapper.insertAttend(attend);
            return attend;
        }else{
            List<String> attendIds = attendMapper.selectRelationByUserId(userId,null,null);
            if(attendIds != null && attendIds.size() > 0) {
                Attend attend = attendMapper.selectByAttendId(attendIds.get(attendIds.size() - 1));
                return  attend ;
            }
        }
        return  null ;
    }
}
