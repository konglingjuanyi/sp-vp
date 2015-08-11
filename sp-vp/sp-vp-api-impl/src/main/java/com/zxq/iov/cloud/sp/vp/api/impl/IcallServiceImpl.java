package com.zxq.iov.cloud.sp.vp.api.impl;

import com.zxq.iov.cloud.sp.vp.api.IIcallService;
import com.zxq.iov.cloud.sp.vp.api.IStatusService;
import com.zxq.iov.cloud.sp.vp.api.dto.OtaDto;
import com.zxq.iov.cloud.sp.vp.api.dto.icall.IcallRecordDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehiclePosDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehicleStatusDto;
import com.zxq.iov.cloud.sp.vp.api.impl.assembler.icall.IcallRecordDtoAssembler;
import com.zxq.iov.cloud.sp.vp.common.Constants;
import com.zxq.iov.cloud.sp.vp.dao.call.ICallDaoService;
import com.zxq.iov.cloud.sp.vp.dao.call.ICallRecordDaoService;
import com.zxq.iov.cloud.sp.vp.dao.config.ITboxDaoService;
import com.zxq.iov.cloud.sp.vp.entity.call.Call;
import com.zxq.iov.cloud.sp.vp.entity.call.CallRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 安防 eCall服务实现类
 *
 * @author 叶荣杰
 * create date 2015-6-12 15:30
 * modify date 2015-7-24 13:08
 * @version 0.7, 2015-7-24
 */
@Service
@Qualifier("icallService")
public class IcallServiceImpl extends BaseService implements IIcallService {

    @Autowired
    private ICallDaoService callDaoService;
    @Autowired
    private ICallRecordDaoService callRecordDaoService;
    @Autowired
    private ITboxDaoService tboxDaoService;
    @Autowired
    @Qualifier("statusService")
    private IStatusService statusService;

    private static final Integer RUNNING_STATUS = 1;
    private static final Integer END_STATUS = 2;

    @Override
    public IcallRecordDto startIcall(OtaDto otaDto, List<VehiclePosDto> vehiclePosDtos, Integer icallType,
                                     Integer tboxBatteryStatus, Integer vehicleBatteryStatus) {
        Long callId = updateIcall(otaDto, vehiclePosDtos, icallType, tboxBatteryStatus, vehicleBatteryStatus);
        String callNumber = "4008888888"; // 此处默认的呼叫号码以什么形式获得还不确定
        return new IcallRecordDtoAssembler().toDto(callRecordDaoService.createCallRecord(
                new CallRecord(callId, callNumber, otaDto.getEventCreateTime())));
    }

    @Override
    public void requestIcallStatus(String vin) throws Exception {
        AssertRequired("vin", vin);
        // 暂无业务处理
    }

    @Override
    public Long updateIcall(OtaDto otaDto, List<VehiclePosDto> vehiclePosDtos, Integer icallType,
                            Integer tboxBatteryStatus, Integer vehicleBatteryStatus) {
        Call call;
        List<Call> list = callDaoService.listCallByTboxId(otaDto.getTboxId(), RUNNING_STATUS);
        if(list.size() > 0) {
            call = list.get(0);
        }
        else {
            call = callDaoService.createCall(new Call(tboxDaoService.findVinById(otaDto.getTboxId()),
                    otaDto.getTboxId(), Constants.CALL_TYPE_ICALL, icallType, otaDto.getEventCreateTime()));
        }
        // 位置状态
        for(VehiclePosDto vehiclePosDto : vehiclePosDtos) {
            statusService.updateVehicleStatus(otaDto, Constants.VEHICLE_INFO_SOURCE_ICALL,
                    call.getId(), vehiclePosDto, null, null);
        }
        // 车辆状态及报警
        List<VehicleStatusDto> vehicleStatusDtos = new ArrayList<>();
        vehicleStatusDtos.add(new VehicleStatusDto("tboxBatteryStatus", tboxBatteryStatus));
        vehicleStatusDtos.add(new VehicleStatusDto("vehicleBatteryStatus", vehicleBatteryStatus));
        statusService.updateVehicleStatus(otaDto, Constants.VEHICLE_INFO_SOURCE_ICALL,
                call.getId(), null, vehicleStatusDtos, null);
        return call.getId();
    }

    @Override
    public void requestHangUp(String vin) throws Exception {
        AssertRequired("vin", vin);
        List<Call> calls = callDaoService.listCallByVin(vin, RUNNING_STATUS);
        if(calls.size() > 0) {
            List<CallRecord> callRecords = callRecordDaoService.listCallRecordByCallId(calls.get(0).getId(), RUNNING_STATUS);
            if(callRecords.size() > 0) {
                CallRecord callRecord = callRecords.get(0);
                callRecord.setHangUpTime(new Date());
                callRecord.setStatus(END_STATUS);
                callRecordDaoService.updateCallRecord(callRecord);
            }
        }
    }

    @Override
    public void requestCallBack(String vin, String callNumber) throws Exception {
        AssertRequired("vin", vin);
        List<Call> list = callDaoService.listCallByVin(vin, RUNNING_STATUS);
        if(list.size() > 0) {
            if(null == callNumber) {
                callNumber = "4008888888"; // 此处默认的呼叫号码以什么形式获得还不确定
            }
            callRecordDaoService.createCallRecord(new CallRecord(list.get(0).getId(), callNumber, new Date()));
        }
    }

    @Override
    public void responseCallBack(OtaDto otaDto, Boolean callbackAccepted, Integer rejectReason) {
        if(!callbackAccepted) {
            List<Call> calls = callDaoService.listCallByTboxId(otaDto.getTboxId(), RUNNING_STATUS);
            if(calls.size() > 0) {
                List<CallRecord> callRecords = callRecordDaoService.listCallRecordByCallId(calls.get(0).getId(), RUNNING_STATUS);
                if(callRecords.size() > 0) {
                    CallRecord callRecord = callRecords.get(0);
                    callRecord.setRejectReason(rejectReason);
                    callRecord.setStatus(END_STATUS);
                    callRecordDaoService.updateCallRecord(callRecord);
                }
            }
        }
    }

    @Override
    public void requestCloseIcall(String vin) throws Exception {
        AssertRequired("vin", vin);
        List<Call> calls = callDaoService.listCallByVin(vin, RUNNING_STATUS);
        if(calls.size() > 0) {
            Call call = calls.get(0);
            call.setEndTime(new Date());
            call.setStatus(END_STATUS);
            callDaoService.updateCall(call);
            // 关闭所有未关闭的通话
            List<CallRecord> callRecords = callRecordDaoService.listCallRecordByCallId(call.getId(), RUNNING_STATUS);
            for(CallRecord callRecord : callRecords) {
                callRecord.setStatus(END_STATUS);
                callRecordDaoService.updateCallRecord(callRecord);
            }
        }
    }

    @Override
    public void closeIcall(OtaDto otaDto) {
        List<Call> list = callDaoService.listCallByTboxId(otaDto.getTboxId(), RUNNING_STATUS);
        if(list.size() > 0) {
            Call call = list.get(0);
            call.setEndTime(new Date());
            call.setStatus(END_STATUS);
            callDaoService.updateCall(call);
            // 关闭所有未关闭的通话
            List<CallRecord> callRecords = callRecordDaoService.listCallRecordByCallId(call.getId(), RUNNING_STATUS);
            for(CallRecord callRecord : callRecords) {
                callRecord.setStatus(END_STATUS);
                callRecordDaoService.updateCallRecord(callRecord);
            }
        }
    }
}