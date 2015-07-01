package com.zxq.iov.cloud.sp.vp.api.impl.event;

import com.zxq.iov.cloud.sp.vp.api.exception.StartCodeNotMatchException;
import com.zxq.iov.cloud.sp.vp.dao.event.IEventInstanceDaoService;
import com.zxq.iov.cloud.sp.vp.dao.event.IEventRuleDaoService;
import com.zxq.iov.cloud.sp.vp.dao.event.IStepDefinitionDaoService;
import com.zxq.iov.cloud.sp.vp.entity.event.EventRule;
import com.zxq.iov.cloud.sp.vp.entity.event.StepDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 安防 事件解析器
 *
 * @author 叶荣杰
 * create date 2015-6-5 16:11
 * modify date 2015-6-30 17:39
 * @version 0.5, 2015-6-30
 */
@Service
public class EventParser {

    @Autowired
    private IEventInstanceDaoService eventInstanceDaoService;
    @Autowired
    private IStepDefinitionDaoService stepDefinitionDaoService;
    @Autowired
    private IEventRuleDaoService eventRuleDaoService;

    /**
     * 定位步骤定义
     * @param code              代码
     * @param paramMap          参数MAP
     * @param eventInstanceId   事件实例ID
     * @return                  步骤定义对象
     */
    public StepDefinition findStepDefiniton(String code, Map<String, Object> paramMap, Long eventInstanceId) {
        Long eventDefinitionId = null;
        if(null != eventInstanceId) {
            eventDefinitionId = eventInstanceDaoService.findEventInstanceById(eventInstanceId).getEventDefinitionId();
        }
        List<StepDefinition> list = stepDefinitionDaoService.listStepDefinitionByStartCodeAndEventDefinitionId(code,
                eventDefinitionId);
        if(list.size() > 0) {
            if(list.size() == 1) {
                return list.get(0);
            }
            if(null != paramMap && paramMap.size() > 0) {
                for(StepDefinition stepDefinition : list) {
                    boolean isMatch = true;
                    for(EventRule eventRule : eventRuleDaoService.listEventRuleByStepDefinitionId(stepDefinition.getId())) {
                        isMatch = isMatch && validateRule(paramMap.get(eventRule.getName()), eventRule.getOperator(), eventRule.getValue());
                    }
                    if(isMatch) {
                        return stepDefinition;
                    }
                }
            }
        }
        throw new StartCodeNotMatchException();
    }

    /**
     * 查询步骤对应的业务类
     * @param stepDefinitionId  步骤定义ID
     * @return                  业务类路径
     */
    public String findStepCallbackClass(Long stepDefinitionId) {
        return null;
    }

    /**
     * 验证是否满足规则
     * @param obj           比较对象
     * @param operator      比较操作符
     * @param value         比较值
     * @return
     */
    private boolean validateRule(Object obj, String operator, String value) {
        if(null != obj) {
            if(operator.equals("eq")) {
                return obj.toString().equals(value);
            }
        }
        return false;
    }
}