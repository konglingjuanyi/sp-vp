package com.zxq.iov.cloud.sp.vp.api.impl.proxy;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.saicmotor.telematics.framework.core.exception.ServLayerException;
import com.zxq.iov.cloud.sp.vp.api.IRvcService;
import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.api.dto.rvc.RvcDto;
import com.zxq.iov.cloud.sp.vp.api.dto.rvc.RvcStatusDto;
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
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * 安防 远程控制服务代理实现类
 *
 * @author 叶荣杰
 * create date 2015-6-16 10:45
 * modify date 2015-7-30 10:41
 * @version 0.8, 2015-7-30
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
    public Long requestControl(String requestClient, Long userId, String vin, String command,
                               Map<String, Object> parameters) throws Exception {
        if(StringUtils.isBlank(command) || null == Constants.RVC_CMD_CODE.get(command)) {
            throw new ServLayerException(ExceptionConstants.WRONG_CONTROL_CMD);
        }
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
        event.start(otaDto, paramMap);
        Long controlCommandId = rvcService.requestControl(requestClient, userId, vin, command, parameters);
        bindEventId(controlCommandId, otaDto.getEventId());
        sendQueue(otaDto, new RvcDto(BinaryAndHexUtil.hexStringToByte(Constants.RVC_CMD_CODE.get(command)),
                tboxConfig));
        event.end(otaDto, paramMap, controlCommandId);
        return controlCommandId;
    }

    @Override
    public void cancelControl(String requestClient, Long userId, String vin, String command)
            throws Exception {
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
            rvcService.cancelControl(requestClient, userId, vin, command);
            Map<String, Object> parameter = new HashMap<>();
            parameter.put("cancel", true);
            sendQueue(otaDto, new RvcDto(BinaryAndHexUtil.hexStringToByte(command),
                    convertConfig2Tbox(parameter)));
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

    @Override
    public RvcStatusDto getControlStatus(Long controlCommandId, String vin, Long userId) throws Exception {
        return rvcService.getControlStatus(controlCommandId, vin, userId);
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

    /**
     * 将请求的命令配置参数转换成TBOX能识别的参数
     * @return
     */
    private Map<Integer, byte[]> convertConfig2Tbox(Map<String, Object> parameters) {
        Map<Integer, byte[]> result = new HashMap<>();
        Iterator iterator = parameters.keySet().iterator();
        String key, val = null;
        Integer keyId;
        while(iterator.hasNext()) {
            key = iterator.next().toString();
            keyId = Constants.RVC_CMD_PARAM_ID.get(key);
            result.put(keyId,
                    BinaryAndHexUtil.hexStringToByte(Constants.RVC_CMD_PARAM_VALUE.get(key+"_"+parameters.get(key))));
        }
        return result;
    }

}
