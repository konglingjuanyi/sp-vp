package com.zxq.iov.cloud.sp.vp.api.impl.proxy;

import com.alibaba.dubbo.common.json.JSON;
import com.alibaba.dubbo.common.json.JSONArray;
import com.alibaba.dubbo.common.json.JSONObject;
import com.alibaba.dubbo.common.json.ParseException;
import com.zxq.iov.cloud.sp.vp.api.IRvcService;
import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehicleInfoDto;
import com.zxq.iov.cloud.sp.vp.api.impl.event.IEvent;
import com.zxq.iov.cloud.sp.vp.common.Constants;
import com.zxq.iov.cloud.sp.vp.dao.rvc.IControlCommandDaoService;
import com.zxq.iov.cloud.sp.vp.entity.rvc.ControlCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


/**
 * 安防 远程控制服务代理实现类
 *
 * @author 叶荣杰
 * create date 2015-6-16 10:45
 * modify date 2015-6-18 10:28
 * @version 0.1, 2015-6-16
 */
@Service
@Qualifier("rvcServiceProxy")
public class RvcServiceProxy implements IRvcService {

    @Autowired
    @Qualifier("rvcService")
    private IRvcService rvcService;
    @Autowired
    private IControlCommandDaoService controlCommandDaoService;
    @Autowired
    private IEvent event;

    @Override
    public Long requestControl(Long tboxId, Integer command, String parameter) {
        OtaDto otaDto = new OtaDto(tboxId, Constants.AID_RVC, 1);
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("command", command);
        paramMap.put("cancelFlag", 0);
        if(null != parameter) {
            try {
                JSONArray params = (JSONArray)JSON.parse(parameter);
                JSONObject param;
                for(int i=0; i<params.length(); i++) {
                    param = (JSONObject)params.get(i);
                    if(param.get("id").toString().equals("4")) {
                        paramMap.put("unlockSilentFlag", param.get("value"));
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        Long eventId = event.start(otaDto, paramMap);
        Long controlCommandId = rvcService.requestControl(tboxId, command, parameter);
        bindEventId(controlCommandId, eventId);
        event.end(otaDto, paramMap);
        return controlCommandId;
    }

    @Override
    public void cancelControl(Long controlCommandId) {
        ControlCommand controlCommand = controlCommandDaoService.findControlCommandById(controlCommandId);
        OtaDto otaDto = new OtaDto(controlCommand.getTboxId(), Constants.AID_RVC, 1);
        otaDto.setEventId(controlCommand.getEventId());
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("command", controlCommand.getCode());
        paramMap.put("cancelFlag", 1);
        event.start(otaDto, paramMap);
        rvcService.cancelControl(controlCommandId);
        event.end(otaDto, paramMap);
    }

    @Override
    public void responseControl(Integer rvcStatus, Integer failureType, VehicleInfoDto vehicleInfoDto) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("status", rvcStatus);
        event.start(vehicleInfoDto, paramMap);
        rvcService.responseControl(rvcStatus, failureType, vehicleInfoDto);
        event.end(vehicleInfoDto, paramMap);
    }


    /**
     * 用以为控制命令绑定事件ID
     * @param controlCommandId      控制命令ID
     * @param eventId               事件ID
     */
    public void bindEventId(Long controlCommandId, Long eventId) {
        ControlCommand controlCommand = controlCommandDaoService.findControlCommandById(controlCommandId);
        controlCommand.setEventId(eventId);
        controlCommandDaoService.updateControlCommand(controlCommand);
    }
}
