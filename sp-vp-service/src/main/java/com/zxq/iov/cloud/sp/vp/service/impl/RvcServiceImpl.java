package com.zxq.iov.cloud.sp.vp.service.impl;

import com.alibaba.dubbo.common.json.JSON;
import com.saicmotor.telematics.framework.core.exception.ServLayerException;
import com.zxq.iov.cloud.sp.vp.common.BinaryAndHexUtil;
import com.zxq.iov.cloud.sp.vp.common.Constants;
import com.zxq.iov.cloud.sp.vp.common.ExceptionConstants;
import com.zxq.iov.cloud.sp.vp.dao.config.ITboxDao;
import com.zxq.iov.cloud.sp.vp.dao.rvc.IControlCommandDao;
import com.zxq.iov.cloud.sp.vp.entity.rvc.ControlCommand;
import com.zxq.iov.cloud.sp.vp.entity.status.VehiclePos;
import com.zxq.iov.cloud.sp.vp.entity.status.VehicleStatus;
import com.zxq.iov.cloud.sp.vp.service.IRvcService;
import com.zxq.iov.cloud.sp.vp.service.IStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 安防 远程控制服务实现类
 *
 * @author 叶荣杰
 * create date 2015-6-17 13:40
 * modify date 2015-8-5 17:20
 * @version 0.11, 2015-8-5
 */
@Service
public class RvcServiceImpl extends BaseService implements IRvcService {

    @Autowired
    private IControlCommandDao controlCommandDao;
    @Autowired
    private ITboxDao tboxDao;
    @Autowired
    private IStatusService statusService;

    private static final String RVC_STATUS_PENDING = "0";
    private static final String RVC_STATUS_COMPLETED = "2";
    private static final String RVC_STATUS_FAILED = "3";
    private static final Integer RUNNING_STATUS = 1;
    private static final Integer END_STATUS = 2;

    @Override
    public ControlCommand requestControl(String requestClient, Long userId, String vin, String command,
                               Map<String, Object> parameters, Long eventId) throws ServLayerException {
        AssertRequired("userId,vin,command", userId, vin, command);
        ControlCommand controlCommand = null;
        try {
            controlCommand = new ControlCommand(tboxDao.findTboxIdByVin(vin),
                        vin, requestClient, Constants.RVC_CMD.get(command), Constants.RVC_CMD_CODE.get(command),
                        JSON.json(parameters));
        } catch (IOException e) {
            e.printStackTrace();
        }
        controlCommand.setCommandStatus(RVC_STATUS_PENDING);
        controlCommand.setIsCancel(false);
        controlCommand.setStatus(RUNNING_STATUS);
        if(null != eventId) {
            controlCommand.setEventId(eventId);
        }
        controlCommandDao.createControlCommand(controlCommand);
        pushRequestClient(controlCommand);
        return controlCommand;
    }

    @Override
    public void cancelControl(String requestClient, Long userId, String vin, String command) throws ServLayerException {
        AssertRequired("userId,vin,command", userId, vin, command);
        List<ControlCommand> list = controlCommandDao.listControlCommandByVinAndCommand(vin,
                command, RUNNING_STATUS);
        if(list.size() > 0) {
            ControlCommand controlCommand = list.get(0);
            // 此处另一个用户是否可以取消别的用户的命令？
            controlCommand.setIsCancel(true);
            controlCommand.setCommandStatus(RVC_STATUS_FAILED);
            controlCommand.setStatus(END_STATUS);
            controlCommandDao.updateControlCommand(controlCommand);
            pushRequestClient(controlCommand);
        }
    }

    @Override
    public void updateControlStatus(Long tboxId, byte[] rvcStatus, Integer failureType, VehiclePos vehiclePos,
                                    List<VehicleStatus> vehicleStatuses, Long eventId) throws ServLayerException{
        AssertRequired("eventId,tboxId,rvcStatus", eventId, tboxId, rvcStatus);
        ControlCommand controlCommand = controlCommandDao.findControlCommandByEventId(eventId);
        if(null != controlCommand) {
            controlCommand.setCommandStatus(BinaryAndHexUtil.bytesToHexString(rvcStatus, false));
            if(null != failureType) {
                controlCommand.setFailureType(failureType);
            }
            if(rvcStatus.equals(RVC_STATUS_COMPLETED) || rvcStatus.equals(RVC_STATUS_FAILED)) {
                controlCommand.setStatus(END_STATUS);
            }
            controlCommandDao.updateControlCommand(controlCommand);
            statusService.logVehicleInfo(tboxId, Constants.VEHICLE_INFO_SOURCE_RVC,
                    controlCommand.getId(), vehiclePos, vehicleStatuses, null, null, null);
            pushRequestClient(controlCommand);
        }
    }

    @Override
    public ControlCommand getControlStatus(Long controlCommandId, String vin, Long userId) throws ServLayerException {
        AssertRequired("controlCommandId,vin", controlCommandId, vin);
        ControlCommand controlCommand = controlCommandDao.findControlCommandById(controlCommandId);
        if(null != controlCommand) {
            if(!controlCommand.getVin().equals(vin)) {
                throw new ServLayerException(ExceptionConstants.NO_PRIVILEGE_TO_VEHICLE);
            }
            return controlCommand;
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