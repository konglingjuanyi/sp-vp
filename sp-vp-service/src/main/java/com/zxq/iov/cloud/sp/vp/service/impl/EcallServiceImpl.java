package com.zxq.iov.cloud.sp.vp.service.impl;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.saicmotor.telematics.framework.core.exception.ServLayerException;
import com.zxq.iov.cloud.sp.vp.common.Constants;
import com.zxq.iov.cloud.sp.vp.common.ExceptionConstants;
import com.zxq.iov.cloud.sp.vp.dao.call.ICallDao;
import com.zxq.iov.cloud.sp.vp.dao.call.ICallRecordDao;
import com.zxq.iov.cloud.sp.vp.entity.call.Call;
import com.zxq.iov.cloud.sp.vp.entity.call.CallRecord;
import com.zxq.iov.cloud.sp.vp.entity.status.VehiclePos;
import com.zxq.iov.cloud.sp.vp.entity.status.VehicleStatus;
import com.zxq.iov.cloud.sp.vp.service.IEcallService;
import com.zxq.iov.cloud.sp.vp.service.IStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 安防 eCall服务实现类
 *
 * @author 叶荣杰
 * create date 2015-6-12 11:26
 * modify date 2015-8-18 14:38
 * @version 0.9, 2015-8-18
 */
@Service
public class EcallServiceImpl extends BaseService implements IEcallService {

    @Autowired
    private ICallDao callDao;
    @Autowired
    private ICallRecordDao callRecordDao;
    @Autowired
    private IStatusService statusService;

    private static final Integer RUNNING_STATUS = 1;
    private static final Integer END_STATUS = 2;

    @Override
    public CallRecord start(Long tboxId, List<VehiclePos> vehiclePoses, Integer ecallType,
                                     Integer crashSeverity, Integer tboxBatteryStatus,
                            Integer vehicleBatteryStatus, Date ecallTime) throws ServLayerException {
        AssertRequired("tboxId,vehiclePoses,ecallType,crashSeverity,tboxBatteryStatus,vehicleBatteryStatus",
                tboxId, vehiclePoses, ecallType, crashSeverity, tboxBatteryStatus, vehicleBatteryStatus);
        Call call = update(tboxId, vehiclePoses, ecallType, crashSeverity, tboxBatteryStatus,
                vehicleBatteryStatus, ecallTime);
        String callNumber = "4008888888"; // 此处默认的呼叫号码以什么形式获得还不确定
        return callRecordDao.createCallRecord(new CallRecord(call.getId(), callNumber, ecallTime));
    }

    @Override
    public Call update(Long tboxId, List<VehiclePos> vehiclePoses, Integer ecallType,
                            Integer crashSeverity, Integer tboxBatteryStatus,
                       Integer vehicleBatteryStatus, Date ecallTime) throws ServLayerException {
        AssertRequired("tboxId,vehiclePoses,ecallType,crashSeverity,tboxBatteryStatus,vehicleBatteryStatus",
                tboxId, vehiclePoses, ecallType, crashSeverity, tboxBatteryStatus, vehicleBatteryStatus);
        List<Call> list = callDao.listCallByTboxId(tboxId, RUNNING_STATUS);
        Call call = list.size()>0?list.get(0):callDao.createCall(new Call(findVinById(tboxId),
                tboxId, Constants.CALL_TYPE_ECALL, ecallType, ecallTime));
        for(VehiclePos vehiclePos : vehiclePoses) {
            // 这里的pos可能会和journey的pos重复，是否要规避
            statusService.logVehicleInfo(tboxId, Constants.VEHICLE_INFO_SOURCE_ECALL,
                    call.getId(), vehiclePos, null, null, null, null);
        }
        List<VehicleStatus> vehicleStatuses = new ArrayList<>();
        vehicleStatuses.add(new VehicleStatus("tboxBatteryStatus", tboxBatteryStatus));
        vehicleStatuses.add(new VehicleStatus("vehicleBatteryStatus", vehicleBatteryStatus));
        statusService.logVehicleInfo(tboxId, Constants.VEHICLE_INFO_SOURCE_ECALL, call.getId(), null,
                vehicleStatuses, null, null, null);
        return call;
    }

    @Override
    public void hangUp(String vin) throws ServLayerException {
        AssertRequired("vin", vin);
        Call call = getRunningEcallByVinOrTboxId(vin, null);
        List<CallRecord> callRecords = callRecordDao.listCallRecordByCallId(call.getId(), RUNNING_STATUS);
        if(callRecords.size() > 0) {
            CallRecord callRecord = callRecords.get(0);
            callRecord.setHangUpTime(new Date());
            callRecord.setStatus(END_STATUS);
            callRecordDao.updateCallRecord(callRecord);
        }
    }

    @Override
    public CallRecord callBack(String vin, String callNumber) throws ServLayerException {
        AssertRequired("vin", vin);
        Call call = getRunningEcallByVinOrTboxId(vin, null);
        if(null == callNumber) {
            callNumber = "4008888888"; // 此处默认的呼叫号码以什么形式获得还不确定
        }
        return callRecordDao.createCallRecord(new CallRecord(call.getId(), callNumber, new Date()));
    }

    @Override
    public void responseCallBack(Long tboxId, Boolean callbackAccepted, Integer rejectReason) throws ServLayerException {
        AssertRequired("tboxId,callbackAccepted", tboxId, callbackAccepted);
        if(!callbackAccepted) {
            Call call = getRunningEcallByVinOrTboxId(null, tboxId);
            List<CallRecord> callRecords = callRecordDao.listCallRecordByCallId(call.getId(),
                    RUNNING_STATUS);
            if(callRecords.size() > 0) {
                CallRecord callRecord = callRecords.get(0);
                callRecord.setRejectReason(rejectReason);
                callRecord.setStatus(END_STATUS);
                callRecordDao.updateCallRecord(callRecord);
            }
        }
    }

    @Override
    public void close(String vin) throws ServLayerException {
        AssertRequired("vin", vin);
        close(vin, null);
    }

    @Override
    public void close(Long tboxId) throws ServLayerException {
        AssertRequired("tboxId", tboxId);
        close(null, tboxId);
    }

    /**
     * 关闭eCall，vin或tboxId任选一个输入
     * @param vin                   车辆唯一码
     * @param tboxId                TBOX ID
     * @throws ServLayerException
     */
    private void close(String vin, Long tboxId) throws ServLayerException {
        Call call = getRunningEcallByVinOrTboxId(vin, tboxId);
        call.setEndTime(new Date());
        call.setStatus(END_STATUS);
        callDao.updateCall(call);
        // 关闭所有未关闭的通话
        List<CallRecord> callRecords = callRecordDao.listCallRecordByCallId(call.getId(), RUNNING_STATUS);
        for(CallRecord callRecord : callRecords) {
            callRecord.setStatus(END_STATUS);
            callRecordDao.updateCallRecord(callRecord);
        }
    }

    /**
     * 根据车辆唯一码或TBOX ID来得到激活的bCall对象
     * @param vin       车辆唯一码
     * @param tboxId    TBOX ID
     * @return          呼叫对象
     */
    private Call getRunningEcallByVinOrTboxId(String vin, Long tboxId) throws ServLayerException {
        List<Call> list = StringUtils.isNotEmpty(vin)
                ?callDao.listCallByVinAndType(vin, Constants.CALL_TYPE_ECALL, RUNNING_STATUS)
                :callDao.listCallByTboxId(tboxId, RUNNING_STATUS);
        if(list.size() > 0) {
            return list.get(0);
        }
        else {
            throw new ServLayerException(ExceptionConstants.NO_OPEN_ECALL);
        }
    }
}