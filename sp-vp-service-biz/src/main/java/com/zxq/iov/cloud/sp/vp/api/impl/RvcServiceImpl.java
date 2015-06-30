package com.zxq.iov.cloud.sp.vp.api.impl;

import com.zxq.iov.cloud.sp.vp.api.IRvcService;
import com.zxq.iov.cloud.sp.vp.api.IStatusService;
import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehiclePosDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehicleStatusDto;
import com.zxq.iov.cloud.sp.vp.common.Constants;
import com.zxq.iov.cloud.sp.vp.dao.config.ITboxDaoService;
import com.zxq.iov.cloud.sp.vp.dao.rvc.IControlCommandDaoService;
import com.zxq.iov.cloud.sp.vp.entity.rvc.ControlCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 安防 远程控制服务实现类
 *
 * @author 叶荣杰
 * create date 2015-6-17 13:40
 * modify date 2015-6-29 10:09
 * @version 0.3, 2015-6-19
 */
@Service
@Qualifier("rvcService")
public class RvcServiceImpl implements IRvcService {

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
    public Long requestControl(String vin, String command, String parameter) {
        ControlCommand controlCommand = new ControlCommand(tboxDaoService.findTboxIdByVin(vin),
                vin, Constants.RVC_CMD.get(command), command, parameter);
        controlCommand.setCommandStatus(RVC_STATUS_PENDING);
        controlCommand.setIsCancel(false);
        controlCommand.setStatus(RUNNING_STATUS);
        controlCommandDaoService.createControlCommand(controlCommand);
        return controlCommand.getId();
    }

    @Override
    public void cancelControl(String vin, String command) {
        List<ControlCommand> list = controlCommandDaoService.listControlCommandByVinAndCommand(vin,
                command, RUNNING_STATUS);
        if(list.size() > 0) {
            ControlCommand controlCommand = list.get(0);
            controlCommand.setIsCancel(true);
            controlCommand.setStatus(END_STATUS);
            controlCommandDaoService.updateControlCommand(controlCommand);
        }
    }

    @Override
    public void updateControlStatus(OtaDto otaDto, String rvcStatus, Integer failureType,
                                    VehiclePosDto vehiclePosDto, List<VehicleStatusDto> vehicleStatusDtos) {
        if(null != otaDto.getEventId()) {
            ControlCommand controlCommand = controlCommandDaoService.findControlCommandByEventId(otaDto.getEventId());
            if(null != controlCommand) {
                controlCommand.setCommandStatus(rvcStatus);
                if(null != failureType) {
                    controlCommand.setFailureType(failureType);
                }
                if(rvcStatus.equals(RVC_STATUS_COMPLETED) || rvcStatus.equals(RVC_STATUS_FAILED)) {
                    controlCommand.setStatus(END_STATUS);
                }
                controlCommandDaoService.updateControlCommand(controlCommand);
                statusService.updateVehicleStatus(otaDto, Constants.VEHICLE_INFO_SOURCE_RVC,
                        controlCommand.getId(), vehiclePosDto, vehicleStatusDtos);
            }
        }

    }

}
