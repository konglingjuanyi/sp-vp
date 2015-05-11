package com.zxq.iov.cloud.sp.vp.api.impl.event;

import com.alibaba.dubbo.common.json.JSON;
import com.alibaba.dubbo.common.json.ParseException;
import com.zxq.iov.cloud.sp.vp.api.dto.EventDto;
import com.zxq.iov.cloud.sp.vp.api.dto.KeyDto;
import com.zxq.iov.cloud.sp.vp.common.OtaConstants;
import com.zxq.iov.cloud.sp.vp.dao.IEventDaoService;
import com.zxq.iov.cloud.sp.vp.dao.IEventParameterDaoService;
import com.zxq.iov.cloud.sp.vp.dao.ITaskDaoService;
import com.zxq.iov.cloud.sp.vp.dao.IStepDaoService;
import com.zxq.iov.cloud.sp.vp.entity.event.Event;
import com.zxq.iov.cloud.sp.vp.entity.event.EventParameter;
import com.zxq.iov.cloud.sp.vp.entity.event.Step;
import com.zxq.iov.cloud.sp.vp.entity.event.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 安防 交换密码事件类
 *
 * @author 叶荣杰
 * create date 2015-4-23 11:08
 * @version 0.2, 2015-4-23
 */
@Service
@Qualifier("encryptionEvent")
public class EncryptionEvent extends AbstractEvent {

    @Autowired
    private IEventDaoService eventDaoService;
    @Autowired
    private ITaskDaoService taskDaoService;
    @Autowired
    private IStepDaoService stepDaoService;
    @Autowired
    private IEventParameterDaoService eventParameterDaoService;

    private static final Map<Integer, Integer> MID = new HashMap<>();
    static {
        MID.put(11, 12);
        MID.put(12, 13);
        MID.put(13, 14);
    }

    public EncryptionEvent() {
        super.aid = OtaConstants.OTA_CONFIGURATION;
        super.eventCode = "1";
        super.taskCode = "1";
        super.isExclusive = true;
        super.isContinue = true;
    }

    @Override
    public void startEvent(EventDto eventDto) {
        if(super.checkEvent(eventDto)) {
            Event event = super.createEvent(eventDto);
            event.setCode(super.eventCode);
            // event.setExpirationTime(); // 未确定具体超时时间
            eventDaoService.createEvent(event);
            eventDto.setEventId(event.getId());
            // 推送第三方轮询来触发超时机制
        }
    }

    @Override
    public void finishEvent(EventDto eventDto) {
        super.finishTask(eventDto); // 完成任务
        super.finishEvent(eventDto); // 完成事件
    }

    @Override
    public void startTask(EventDto eventDto) {
        if(super.checkTask(eventDto)) {
            Task task = super.createTask(eventDto);
            task.setSort(0L);
            task.setCode(super.taskCode);
            // task.setExpirationTime();  // 未确定具体超时时间
            taskDaoService.createTask(task);
            eventDto.setTaskId(task.getId());

            Event event = task.getEvent(); // 激活当前任务
            event.setActiveTask(task);
            eventDaoService.updateEvent(event);
            // 推送第三方轮询来出发超时机制
        }
    }

    @Override
    public Object startStep(EventDto eventDto, Class clazz) {
        Object result = null;
        if(super.checkStep(eventDto)) {
            Step step = super.createStep(eventDto);
            step.setRespAid(super.aid);
            step.setRespMid(MID.get(step.getMid()));
            stepDaoService.createStep(step);

            Task task = step.getTask(); // 激活当前步骤
            task.setActiveStep(step);
            taskDaoService.updateTask(task);
        }
        else {
            Task task = taskDaoService.findTaskById(eventDto.getTaskId());
            EventParameter eventParameter = eventParameterDaoService.findEventParameterByName(
                    "result", null, null, task.getActiveStep().getId());
            try {
                result =  JSON.parse(eventParameter.getValue(), clazz);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}