package com.zxq.iov.cloud.sp.vp.api.impl.proxy;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.saicmotor.telematics.framework.core.exception.ServLayerException;
import com.zxq.iov.cloud.sp.vp.api.IRvcService;
import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.api.dto.rvc.RvcDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehiclePosDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehicleStatusDto;
import com.zxq.iov.cloud.sp.vp.api.impl.event.IEvent;
import com.zxq.iov.cloud.sp.vp.common.BinaryAndHexUtil;
import com.zxq.iov.cloud.sp.vp.common.Constants;
import com.zxq.iov.cloud.sp.vp.common.ExceptionConstants;
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
 * modify date 2015-7-24 10:51
 * @version 0.7, 2015-7-24
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
    public Long requestControl(Long userId, String vin, String command,
                               Map<String, Object> parameters) throws Exception {
        if(StringUtils.isBlank(command) || null == Constants.RVC_CMD_CODE.get(command)) {
            throw new ServLayerException(ExceptionConstants.WRONG_CONTROL_CMD);
        }
        OtaDto otaDto = new OtaDto(getTboxId(vin), vin, Constants.AID_RVC, 1);
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("command", Constants.RVC_CMD_CODE.get(command));
        paramMap.put("cancelFlag", 0);
        if(null != parameters && null != parameters.get("paramid")) {
            if(parameters.get("paramid").toString().equals("4")) {
                paramMap.put("unlockSilentFlag", parameters.get("paramvalue"));
            }
        }
        event.start(otaDto, paramMap);
        Long controlCommandId = rvcService.requestControl(userId, vin, command, parameters);
        bindEventId(controlCommandId, otaDto.getEventId());
        sendQueue(otaDto, new RvcDto(BinaryAndHexUtil.hexStringToByte(Constants.RVC_CMD_CODE.get(command)),
                parameters));
        event.end(otaDto, paramMap, controlCommandId);
        return controlCommandId;
    }

    @Override
    public void cancelControl(String vin, String command) throws Exception {
        List<ControlCommand> list = controlCommandDaoService.listControlCommandByVinAndCommand(vin,
                command, RUNNING_STATUS);
        if(list.size() > 0) {
            OtaDto otaDto = new OtaDto(getTboxId(vin), vin, Constants.AID_RVC, 1);
            Long eventId = list.get(0).getEventId();
            otaDto.setEventId(eventId);
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("command", list.get(0).getCode());
            paramMap.put("cancelFlag", 1);
            event.start(otaDto, paramMap);
            rvcService.cancelControl(vin, command);
            Map<String, Object> parameter = new HashMap<>();
            parameter.put("paramid", 255);
            parameter.put("paramvalue", 1);
            sendQueue(otaDto, new RvcDto(BinaryAndHexUtil.hexStringToByte(command), parameter));
            event.end(otaDto, paramMap);
        }
    }

    @Override
    public void updateControlStatus(OtaDto otaDto, byte[] rvcStatus, Integer failureType,
                                    VehiclePosDto vehiclePosDto, List<VehicleStatusDto> vehicleStatusDtos)
            throws Exception {
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
