package com.edu.service.impl;

import com.edu.commons.vo.PageVo;
import com.edu.mapper.LeaveMapper;
import com.edu.mapper.LeavelogMapper;
import com.edu.pojo.Leave;
import com.edu.pojo.Leavelog;
import com.edu.service.LeaveService;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class LeaveServiceImpl implements LeaveService {

    @Autowired
    private LeaveMapper lm;
    @Autowired
    private LeavelogMapper llm;
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private HistoryService historyService;

    @Override
    public void initPro(){
       try {
           Deployment deployment = repositoryService.createDeploymentQuery().deploymentName("leave").singleResult();
           if(deployment == null){
               repositoryService.createDeployment().name("leave").addClasspathResource("flows/leave.bpmn").deploy();
           }
       }catch (Exception e){

       }
    }

    //发起任务
    @Override
    public void createPro(Leave leave,String uname,String gname) {
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("days",leave.getDays());
        param.put("Staff",uname);//第一个任务的审批人--自己
        param.put("Group",gname);//第二个任务的审批人--领导
        //发布流程
        ProcessInstance leaveProcess = runtimeService.startProcessInstanceByKey("leaveProcess", param);
        //根据流程实例查询任务
        Task task = taskService.createTaskQuery().processInstanceId(leaveProcess.getProcessInstanceId()).singleResult();
        leave.setTaskid(task.getId());

        //保存请假记录
        lm.insert(leave);
        //保存日志
        Leavelog leavelog = new Leavelog();
        leavelog.setLid(leave.getId());
        leavelog.setMsg("新增请假记录");
        leavelog.setType(0);
        leavelog.setUid(leave.getUid());
        llm.insert(leavelog);
    }

    //代办事项
    @Override
    public PageVo<Task> doingTask(String uname) {
        List<Task> list = taskService.createTaskQuery().taskAssignee(uname).list();
        return PageVo.createPage(list,list.size());
    }


    //审批
    @Override
    public void confirmTask(String tid, Integer lid,Integer flag) {
        taskService.complete(tid);
        lm.udateFlagByLid(lid,flag);

        Leavelog log = new Leavelog();
        log.setLid(lid);
        switch (flag){
            case 1:
                break;
        }
    }

    @Override
    public PageVo<HistoricTaskInstance> queryHistory(Integer uid, Integer currentpage, Integer limit) {
        return null;
    }

    @Override
    public PageVo<Leavelog> queryLeavelog(Integer currentpage, Integer limit) {
        return null;
    }
}
