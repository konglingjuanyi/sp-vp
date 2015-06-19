package com.zxq.iov.cloud.sp.vp.api.impl;

import com.zxq.iov.cloud.sp.vp.api.IRvcService;
import com.zxq.iov.cloud.sp.vp.api.IStatusService;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehicleInfoDto;
import com.zxq.iov.cloud.sp.vp.common.Constants;
import com.zxq.iov.cloud.sp.vp.dao.rvc.IControlCommandDaoService;
import com.zxq.iov.cloud.sp.vp.entity.rvc.ControlCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * 安防 远程控制服务实现类
 *
 * @author 叶荣杰
 * create date 2015-6-17 13:40
 * modify date 2015-6-19 9:44
 * @version 0.3, 2015-6-19
 */
@Service
@Qualifier("rvcService")
public class RvcServiceImpl implements IRvcService {

    @Autowired
    private IControlCommandDaoService controlCommandDaoService;
    @Autowired
    @Qualifier("statusService")
    private IStatusService statusService;

    @Override
    public Long requestControl(Long tboxId, Integer command, String parameter) {
        ControlCommand controlCommand = new ControlCommand(tboxId, Constants.RVC_CMD.get(command),
                    command, parameter);
        controlCommand.setStatus(Constants.RVC_STATUS_PENDING);
        controlCommand.setIsCancel(false);
        controlCommandDaoService.createControlCommand(controlCommand);
        return controlCommand.getId();
    }

    @Override
    public void cancelControl(Long controlCommandId) {
        ControlCommand controlCommand = controlCommandDaoService.findControlCommandById(controlCommandId);
        controlCommand.setIsCancel(true);
        controlCommandDaoService.updateControlCommand(controlCommand);
    }

    @Override
    public void responseControl(Integer rvcStatus, Integer failureType, VehicleInfoDto vehicleInfoDto) {
        if(null != vehicleInfoDto.getEventId()) {
            ControlCommand controlCommand = controlCommandDaoService.findControlCommandByEventId(vehicleInfoDto.getEventId());
            if(null != controlCommand) {
                controlCommand.setStatus(rvcStatus);
                if(null != failureType) {
                    controlCommand.setFailureType(failureType);
                }
                controlCommandDaoService.updateControlCommand(controlCommand);
                vehicleInfoDto.setSourceType(Constants.VEHICLE_INFO_SOURCE_RVC);
                vehicleInfoDto.setSourceId(controlCommand.getId());
                statusService.updateVehicleStatus(vehicleInfoDto);
            }
        }

    }

}
