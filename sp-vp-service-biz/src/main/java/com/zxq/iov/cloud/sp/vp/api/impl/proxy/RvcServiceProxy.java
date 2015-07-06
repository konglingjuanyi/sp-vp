package com.zxq.iov.cloud.sp.vp.api.impl.proxy;

import com.alibaba.dubbo.common.json.JSON;
import com.alibaba.dubbo.common.json.JSONArray;
import com.alibaba.dubbo.common.json.JSONObject;
import com.alibaba.dubbo.common.json.ParseException;
import com.zxq.iov.cloud.sp.vp.api.IRvcService;
import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.api.dto.rvc.RvcDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehiclePosDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehicleStatusDto;
import com.zxq.iov.cloud.sp.vp.api.impl.event.IEvent;
import com.zxq.iov.cloud.sp.vp.common.Constants;
import com.zxq.iov.cloud.sp.vp.dao.rvc.IControlCommandDaoService;
import com.zxq.iov.cloud.sp.vp.entity.rvc.ControlCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 安防 远程控制服务代理实现类
 *
 * @author 叶荣杰
 * create date 2015-6-16 10:45
 * modify date 2015-7-6 16:51
 * @version 0.4, 2015-7-6
 */
@Service
@Qualifier("rvcServiceProxy")
public class RvcServiceProxy extends BaseProxy implements IRvcService {

    @Autowired
    @Qualifier("rvcService")
    private IRvcService rvcService;
    @Autowired
    private IControlCommandDaoService controlCommandDaoService;
    @Autowired
    private IEvent event;

    private static final Integer RUNNING_STATUS = 1;

    @Override
    public Long requestControl(String vin, String command, String parameter) {
        OtaDto otaDto = new OtaDto(vin, Constants.AID_RVC, 1);
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
        event.start(otaDto, paramMap);
        Long controlCommandId = rvcService.requestControl(vin, command, parameter);
        bindEventId(controlCommandId, otaDto.getEventId());
        sendQueue(otaDto, new RvcDto(command, parameter));
        event.end(otaDto, paramMap, controlCommandId);
        return controlCommandId;
    }

    @Override
    public void cancelControl(String vin, String command) {
        List<ControlCommand> list = controlCommandDaoService.listControlCommandByVinAndCommand(vin,
                command, RUNNING_STATUS);
        if(list.size() > 0) {
            OtaDto otaDto = new OtaDto(vin, Constants.AID_RVC, 1);
            Long eventId = list.get(0).getEventId();
            otaDto.setEventId(eventId);
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("command", list.get(0).getCode());
            paramMap.put("cancelFlag", 1);
            event.start(otaDto, paramMap);
            rvcService.cancelControl(vin, command);
            sendQueue(otaDto, new RvcDto(command, "{'cancelFlag', 1}"));
            event.end(otaDto, paramMap);
        }
    }

    @Override
    public void updateControlStatus(OtaDto otaDto, String rvcStatus, Integer failureType,
                                    VehiclePosDto vehiclePosDto, List<VehicleStatusDto> vehicleStatusDtos) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("status", rvcStatus);
        event.start(otaDto, paramMap);
        rvcService.updateControlStatus(otaDto, rvcStatus, failureType, vehiclePosDto, vehicleStatusDtos);
        event.end(otaDto, paramMap);
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
