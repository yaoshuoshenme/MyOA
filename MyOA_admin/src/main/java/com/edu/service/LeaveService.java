package com.edu.service;

import com.edu.commons.vo.PageVo;
import com.edu.pojo.Leave;
import com.edu.pojo.Leavelog;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.task.Task;

public interface LeaveService {
    //初始化
    void initPro();

    //发起申请
    void createPro(Leave leave,String uname,String gname);
    //代办事项
    PageVo<Task> doingTask(String uname);
    //确认申请
    void confirmTask(String tid, Integer lid,Integer flag);
    //请求流历史
    PageVo<HistoricTaskInstance> queryHistory(Integer uid, Integer currentpage, Integer limit);
    //查看日志
    PageVo<Leavelog> queryLeavelog(Integer currentpage, Integer limit);

}
