package com.zxq.iov.cloud.sp.vp.api.impl;

import com.alibaba.dubbo.common.json.JSON;
import com.saicmotor.telematics.framework.core.exception.ServLayerException;
import com.zxq.iov.cloud.sp.vp.api.IRvcService;
import com.zxq.iov.cloud.sp.vp.api.IStatusService;
import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.api.dto.rvc.RvcStatusDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehiclePosDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehicleStatusDto;
import com.zxq.iov.cloud.sp.vp.api.impl.assembler.rvc.RvcStatusDtoAssembler;
import com.zxq.iov.cloud.sp.vp.common.BinaryAndHexUtil;
import com.zxq.iov.cloud.sp.vp.common.Constants;
import com.zxq.iov.cloud.sp.vp.common.ExceptionConstants;
import com.zxq.iov.cloud.sp.vp.dao.config.ITboxDaoService;
import com.zxq.iov.cloud.sp.vp.dao.rvc.IControlCommandDaoService;
import com.zxq.iov.cloud.sp.vp.entity.rvc.ControlCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 安防 远程控制服务实现类
 *
 * @author 叶荣杰
 * create date 2015-6-17 13:40
 * modify date 2015-7-30 9:49
 * @version 0.10, 2015-7-30
 */
@Service
@Qualifier("rvcService")
public class RvcServiceImpl extends BaseService implements IRvcService {

    @Autowired
    private IControlCommandDaoService controlCommandDaoService;
    @Autowired
    private ITboxDaoService tboxDaoService;
    @Autowired
    @Qualifier("statusService")
    private IStatusService statusService;

    private static final String RVC_STATUS_PENDING = "0";
    private static final String RVC_STATUS_COMPLETED = "2";
    private static final String RVC_STATUS_FAILED = "3";
    private static final Integer RUNNING_STATUS = 1;
    private static final Integer END_STATUS = 2;

    @Override
    public Long requestControl(String requestClient, Long userId, String vin, String command,
                               Map<String, Object> parameters) throws Exception {
        AssertRequired("userId,vin,command", userId, vin, command);
        ControlCommand controlCommand = new ControlCommand(tboxDaoService.findTboxIdByVin(vin),
                    vin, requestClient, Constants.RVC_CMD.get(command), Constants.RVC_CMD_CODE.get(command),
                    JSON.json(parameters));
        controlCommand.setCommandStatus(RVC_STATUS_PENDING);
        controlCommand.setIsCancel(false);
        controlCommand.setStatus(RUNNING_STATUS);
        controlCommandDaoService.createControlCommand(controlCommand);
        pushRequestClient(controlCommand);
        return controlCommand.getId();
    }

    @Override
    public void cancelControl(String requestClient, Long userId, String vin, String command) {
        List<ControlCommand> list = controlCommandDaoService.listControlCommandByVinAndCommand(vin,
                command, RUNNING_STATUS);
        if(list.size() > 0) {
            ControlCommand controlCommand = list.get(0);
            // 此处另一个用户是否可以取消别的用户的命令？
            controlCommand.setIsCancel(true);
            controlCommand.setStatus(END_STATUS);
            controlCommandDaoService.updateControlCommand(controlCommand);
            pushRequestClient(controlCommand);
        }
    }

    @Override
    public void updateControlStatus(OtaDto otaDto, byte[] rvcStatus, Integer failureType,
                                    VehiclePosDto vehiclePosDto, List<VehicleStatusDto> vehicleStatusDtos) {
        if(null != otaDto.getEventId()) {
            ControlCommand controlCommand = controlCommandDaoService.findControlCommandByEventId(otaDto.getEventId());
            if(null != controlCommand) {
                controlCommand.setCommandStatus(BinaryAndHexUtil.bytesToHexString(rvcStatus, false));
                if(null != failureType) {
                    controlCommand.setFailureType(failureType);
                }
                if(rvcStatus.equals(RVC_STATUS_COMPLETED) || rvcStatus.equals(RVC_STATUS_FAILED)) {
                    controlCommand.setStatus(END_STATUS);
                }
                controlCommandDaoService.updateControlCommand(controlCommand);
                statusService.updateVehicleStatus(otaDto, Constants.VEHICLE_INFO_SOURCE_RVC,
                        controlCommand.getId(), vehiclePosDto, vehicleStatusDtos, null);
                pushRequestClient(controlCommand);
            }
        }

    }

    @Override
    public RvcStatusDto getControlStatus(Long controlCommandId, String vin, Long userId) throws Exception {
        AssertRequired("controlCommandId,vin", controlCommandId, vin);
        ControlCommand controlCommand = controlCommandDaoService.findControlCommandById(controlCommandId);
        if(null != controlCommand) {
            if(!controlCommand.getVin().equals(vin)) {
                throw new ServLayerException(ExceptionConstants.NO_PRIVILEGE_TO_VEHICLE);
            }
            return new RvcStatusDtoAssembler().toDto(controlCommand);
        }
        throw new ServLayerException(ExceptionConstants.CONTROL_CMD_NOT_EXIST);
    }

    /**
     * 推送请求终端
     * @param controlCommand    控制命令对象
     */
    private void pushRequestClient(ControlCommand controlCommand) {

    }
}
