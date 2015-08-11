package com.zxq.iov.cloud.sp.vp.api.impl;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.saicmotor.telematics.framework.core.exception.ServLayerException;
import com.zxq.iov.cloud.sp.vp.api.IRvcApi;
import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.api.dto.rvc.RvcDto;
import com.zxq.iov.cloud.sp.vp.api.dto.rvc.RvcStatusDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehiclePosDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehicleStatusDto;
import com.zxq.iov.cloud.sp.vp.api.impl.assembler.rvc.RvcStatusDtoAssembler;
import com.zxq.iov.cloud.sp.vp.api.impl.assembler.status.VehiclePosDtoAssembler;
import com.zxq.iov.cloud.sp.vp.api.impl.assembler.status.VehicleStatusDtoAssembler;
import com.zxq.iov.cloud.sp.vp.common.BinaryAndHexUtil;
import com.zxq.iov.cloud.sp.vp.common.Constants;
import com.zxq.iov.cloud.sp.vp.common.ExceptionConstants;
import com.zxq.iov.cloud.sp.vp.service.IEventService;
import com.zxq.iov.cloud.sp.vp.service.IRvcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 安防 远程控制服务实现类
 *
 * @author 叶荣杰
 * create date 2015-6-17 13:40
 * modify date 2015-8-7 14:01
 * @version 0.12, 2015-8-7
 */
@Service
public class RvcApiImpl extends BaseApi implements IRvcApi {

    @Autowired
    private IRvcService rvcService;
    @Autowired
    private IEventService eventService;

    @Override
    public Long requestControl(String requestClient, Long userId, String vin, String command,
                               Map<String, Object> parameters) throws ServLayerException {
        AssertRequired("userId,vin,command", userId, vin, command);
        OtaDto otaDto = new OtaDto(getTboxId(vin), vin, Constants.AID_RVC, 1);
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("command", Constants.RVC_CMD_CODE.get(command));
        paramMap.put("cancelFlag", 0);
        Map<Integer, byte[]> tboxConfig = null;
        if(null != parameters) {
            tboxConfig = convertConfig2Tbox(parameters);
            if(command.equals(Constants.RVC_CMD_VEHICLE_UNLOCK)) {
                paramMap.put("unlockSilentFlag", parameters.get("silent_flag"));
            }
        }
        Long eventId = eventService.start(vin, Constants.AID_RVC + "1", paramMap, null);
        otaDto.setEventId(eventId);
        Long controlCommandId = rvcService.requestControl(requestClient, userId, vin, command, parameters, eventId).getId();
        sendQueue(otaDto, new RvcDto(BinaryAndHexUtil.hexStringToByte(Constants.RVC_CMD_CODE.get(command)),
                tboxConfig));
        eventService.end(vin, Constants.AID_RVC + "1", paramMap, controlCommandId, eventId);
        return controlCommandId;
    }

    @Override
    public void cancelControl(String requestClient, Long userId, String vin,
                              String command) throws ServLayerException {
        AssertRequired("userId,vin,command", userId, vin, command);
        OtaDto otaDto = new OtaDto(getTboxId(vin), vin, Constants.AID_RVC, 1);
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("command", Constants.RVC_CMD_CODE.get(command));
        paramMap.put("cancelFlag", 1);
        Long eventId = eventService.start(vin, Constants.AID_RVC + "1", paramMap, null);
        rvcService.cancelControl(requestClient, userId, vin, command);
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("cancel", true);
        otaDto.setEventId(eventId);
        sendQueue(otaDto, new RvcDto(BinaryAndHexUtil.hexStringToByte(Constants.RVC_CMD_CODE.get(command)),
                convertConfig2Tbox(parameter)));
        eventService.end(vin, Constants.AID_RVC + "1", paramMap, eventId);
    }

    @Override
    public void updateControlStatus(OtaDto otaDto, byte[] rvcStatus, Integer failureType,
                                    VehiclePosDto vehiclePosDto,
                                    List<VehicleStatusDto> vehicleStatusDtos) throws ServLayerException {
        AssertRequired("otaDto,rvcStatus", otaDto, rvcStatus);
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("status", rvcStatus);
        Long eventId = eventService.start(getVin(otaDto), getCode(otaDto), otaDto.getEventId());
        rvcService.updateControlStatus(otaDto.getTboxId(), rvcStatus, failureType,
                new VehiclePosDtoAssembler().fromDto(vehiclePosDto),
                new VehicleStatusDtoAssembler().fromDtoList(vehicleStatusDtos), eventId);
        eventService.end(getVin(otaDto), getCode(otaDto), eventId);
    }

    @Override
    public RvcStatusDto getControlStatus(Long controlCommandId, String vin, Long userId) throws ServLayerException {
        AssertRequired("controlCommandId,vin", controlCommandId, vin);
        return new RvcStatusDtoAssembler().toDto(rvcService.getControlStatus(controlCommandId, vin, userId));
    }

    /**
     * 将请求的命令配置参数转换成TBOX能识别的参数
     * @return
     */
    private Map<Integer, byte[]> convertConfig2Tbox(Map<String, Object> parameters) throws ServLayerException {
        Map<Integer, byte[]> result = new HashMap<>();
        Iterator iterator = parameters.keySet().iterator();
        String key, val = null;
        Integer keyId;
        while(iterator.hasNext()) {
            key = iterator.next().toString();
            keyId = Constants.RVC_CMD_PARAM_ID.get(key);
            val = Constants.RVC_CMD_PARAM_VALUE.get(key + "_" + parameters.get(key));
            if(StringUtils.isBlank(val)) {
                throw new ServLayerException(ExceptionConstants.WRONG_CONTROL_CMD_PARAM);
            }
            result.put(keyId,
                    BinaryAndHexUtil.hexStringToByte(val));
        }
        return result;
    }

}