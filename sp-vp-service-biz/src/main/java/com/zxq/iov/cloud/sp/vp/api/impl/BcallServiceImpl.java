package com.zxq.iov.cloud.sp.vp.api.impl;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.zxq.iov.cloud.sp.vp.api.IBcallService;
import com.zxq.iov.cloud.sp.vp.api.IStatusService;
import com.zxq.iov.cloud.sp.vp.api.dto.bcall.BcallDto;
import com.zxq.iov.cloud.sp.vp.api.dto.bcall.BcallRecordDto;
import com.zxq.iov.cloud.sp.vp.api.dto.status.VehicleInfoDto;
import com.zxq.iov.cloud.sp.vp.api.impl.assembler.bcall.BcallDtoAssembler;
import com.zxq.iov.cloud.sp.vp.api.impl.assembler.bcall.BcallRecordDtoAssembler;
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
 * 安防 bCall服务实现类
 *
 * @author 叶荣杰
 * create date 2015-6-11 10:42
 * modify date
 * @version 0.1, 2015-6-11
 */
@Service
@Qualifier("bcallService")
public class BcallServiceImpl implements IBcallService {

    @Autowired
    private ICallDaoService callDaoService;
    @Autowired
    private ICallRecordDaoService callRecordDaoService;
    @Autowired
    @Qualifier("statusService")
    private IStatusService statusService;

    private static final Integer RUNNING_STATUS = 1;
    private static final Integer END_STATUS = 2;

    @Override
    public BcallRecordDto startBcall(BcallDto bcallDto, List<VehicleInfoDto> vehicleInfoDtos) {
        Call call;
        List<Call> list = callDaoService.listCallByTboxId(bcallDto.getTboxId(), RUNNING_STATUS);
        if(list.size() == 0) {
            call = new BcallDtoAssembler().fromDto(bcallDto);
            call.setStartTime(bcallDto.getEventCreateTime());
            call.setStatus(RUNNING_STATUS);
            call = callDaoService.createCall(call);
        }
        else {
            call = list.get(0);
        }
        for(VehicleInfoDto vehicleInfoDto : vehicleInfoDtos) {
            vehicleInfoDto.setSourceType(Constants.VEHICLE_INFO_SOURCE_BCALL);
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
        return new BcallRecordDtoAssembler().toDto(callRecord);
    }

    @Override
    public void requestBcallStatus(Long tboxId) {
        // 此处向queue发送请求命令
    }

    @Override
    public void updateBcall(BcallDto bcallDto, List<VehicleInfoDto> vehicleInfoDtos) {
        Call call;
        if(null != bcallDto.getId()) {
            call = callDaoService.findCallById(bcallDto.getId());
        }
        else {
            call = callDaoService.listCallByTboxId(bcallDto.getTboxId(), RUNNING_STATUS).get(0);
        }
        for(VehicleInfoDto vehicleInfoDto : vehicleInfoDtos) {
            vehicleInfoDto.setSourceType(Constants.VEHICLE_INFO_SOURCE_BCALL);
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
    public void responseCallBack(BcallRecordDto bcallRecordDto) {
        if(StringUtils.isNotEmpty(bcallRecordDto.getErrorCode())) {
            Call call = callDaoService.listCallByTboxId(bcallRecordDto.getTboxId(), RUNNING_STATUS).get(0);
            CallRecord callRecord = callRecordDaoService.listCallRecordByCallId(call.getId(), RUNNING_STATUS).get(0);
            callRecord.setErrorCode(bcallRecordDto.getErrorCode());
            callRecord.setStatus(END_STATUS);
            callRecordDaoService.updateCallRecord(callRecord);
        }

    }

    @Override
    public void requestCloseBcall(Long tboxId, Long callId) {
        Call call = callDaoService.findCallById(callId);
        call.setEndTime(new Date());
        call.setStatus(END_STATUS);
        callDaoService.updateCall(call);
        // 此处向queue发送请求命令
    }

    @Override
    public void closeBcall(BcallDto bcallDto) {
        Call call = callDaoService.listCallByTboxId(bcallDto.getTboxId(), RUNNING_STATUS).get(0);
        call.setEndTime(new Date());
        call.setStatus(END_STATUS);
        callDaoService.updateCall(call);
    }
}
