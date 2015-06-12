package com.zxq.iov.cloud.sp.vp.api.impl;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.zxq.iov.cloud.sp.vp.api.IIcallService;
import com.zxq.iov.cloud.sp.vp.api.IStatusService;
import com.zxq.iov.cloud.sp.vp.api.dto.icall.IcallDto;
import com.zxq.iov.cloud.sp.vp.api.dto.icall.IcallRecordDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehicleInfoDto;
import com.zxq.iov.cloud.sp.vp.api.impl.assembler.icall.IcallDtoAssembler;
import com.zxq.iov.cloud.sp.vp.api.impl.assembler.icall.IcallRecordDtoAssembler;
import com.zxq.iov.cloud.sp.vp.common.Constants;
import com.zxq.iov.cloud.sp.vp.dao.call.ICallDaoService;
import com.zxq.iov.cloud.sp.vp.dao.call.ICallRecordDaoService;
import com.zxq.iov.cloud.sp.vp.entity.call.Call;
import com.zxq.iov.cloud.sp.vp.entity.call.CallRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 安防 eCall服务实现类
 *
 * @author 叶荣杰
 * create date 2015-6-12 15:30
 * modify date
 * @version 0.1, 2015-6-12
 */
@Service
@Qualifier("icallService")
public class IcallServiceImpl implements IIcallService {

    @Autowired
    private ICallDaoService callDaoService;
    @Autowired
    private ICallRecordDaoService callRecordDaoService;
    @Autowired
    private IStatusService statusService;

    private static final Integer RUNNING_STATUS = 1;
    private static final Integer END_STATUS = 2;

    @Override
    public IcallRecordDto startIcall(IcallDto icallDto, List<VehicleInfoDto> vehicleInfoDtos) {
        Call call;
        List<Call> list = callDaoService.listCallByTboxId(icallDto.getTboxId(), RUNNING_STATUS);
        if(list.size() == 0) {
            call = new IcallDtoAssembler().fromDto(icallDto);
            call.setStartTime(icallDto.getEventCreateTime());
            call.setStatus(RUNNING_STATUS);
            call = callDaoService.createCall(call);
        }
        else {
            call = list.get(0);
        }
        for(VehicleInfoDto vehicleInfoDto : vehicleInfoDtos) {
            vehicleInfoDto.setSourceType(Constants.VEHICLE_INFO_SOURCE_ICALL);
            vehicleInfoDto.setSourceId(call.getId());
            statusService.updateVehicleStatus(vehicleInfoDto);
        }
        String callNumber = "4008888888"; // 此处callNumber以什么形式获得还不确定
        CallRecord callRecord = new CallRecord();
        callRecord.setCallId(call.getId());
        callRecord.setCallTime(new Date());
        callRecord.setCallNumber(callNumber);
        callRecord.setStatus(RUNNING_STATUS);
        callRecord = callRecordDaoService.createCallRecord(callRecord);
        return new IcallRecordDtoAssembler().toDto(callRecord);
    }

    @Override
    public void requestIcallStatus(Long tboxId) {
        // 此处向queue发送请求命令
    }

    @Override
    public void updateIcall(IcallDto icallDto, List<VehicleInfoDto> vehicleInfoDtos) {
        Call call;
        if(null != icallDto.getId()) {
            call = callDaoService.findCallById(icallDto.getId());
        }
        else {
            call = callDaoService.listCallByTboxId(icallDto.getTboxId(), RUNNING_STATUS).get(0);
        }
        for(VehicleInfoDto vehicleInfoDto : vehicleInfoDtos) {
            vehicleInfoDto.setSourceType(Constants.VEHICLE_INFO_SOURCE_ICALL);
            vehicleInfoDto.setSourceId(call.getId());
            statusService.updateVehicleStatus(vehicleInfoDto);
        }
    }

    @Override
    public void requestHangUp(Long tboxId, Long callRecordId) {
        CallRecord callRecord = callRecordDaoService.findCallRecordById(callRecordId);
        callRecord.setHangUpTime(new Date());
        callRecord.setStatus(END_STATUS);
        callRecordDaoService.updateCallRecord(callRecord);
        // 此处向queue发送请求命令
    }

    @Override
    public void requestCallBack(Long tboxId, Long callId, String callNumber) {
        CallRecord callRecord = new CallRecord();
        callRecord.setCallId(callId);
        callRecord.setCallNumber(callNumber);
        callRecord.setCallTime(new Date());
        callRecord.setStatus(RUNNING_STATUS);
        callRecordDaoService.createCallRecord(callRecord);
        // 此处向queue发送请求命令
    }

    @Override
    public void responseCallBack(IcallRecordDto icallRecordDto) {
        if(StringUtils.isNotEmpty(icallRecordDto.getErrorCode())) {
            Call call = callDaoService.listCallByTboxId(icallRecordDto.getTboxId(), RUNNING_STATUS).get(0);
            CallRecord callRecord = callRecordDaoService.listCallRecordByCallId(call.getId(), RUNNING_STATUS).get(0);
            callRecord.setErrorCode(icallRecordDto.getErrorCode());
            callRecord.setStatus(END_STATUS);
            callRecordDaoService.updateCallRecord(callRecord);
        }

    }

    @Override
    public void requestCloseIcall(Long tboxId, Long callId) {
        Call call = callDaoService.findCallById(callId);
        call.setEndTime(new Date());
        call.setStatus(END_STATUS);
        callDaoService.updateCall(call);
        // 此处向queue发送请求命令
    }

    @Override
    public void closeIcall(IcallDto icallDto) {
        Call call = callDaoService.listCallByTboxId(icallDto.getTboxId(), RUNNING_STATUS).get(0);
        call.setEndTime(new Date());
        call.setStatus(END_STATUS);
        callDaoService.updateCall(call);
    }
}
