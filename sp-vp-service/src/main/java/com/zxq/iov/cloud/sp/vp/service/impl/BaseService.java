package com.zxq.iov.cloud.sp.vp.service.impl;

import com.saicmotor.telematics.framework.core.exception.ServLayerException;
import com.zxq.iov.cloud.sp.mds.tcmp.api.ITboxApi;
import com.zxq.iov.cloud.sp.mds.tcmp.api.IVehicleApi;
import com.zxq.iov.cloud.sp.mds.tcmp.api.dto.TboxDto;
import com.zxq.iov.cloud.sp.mds.tcmp.api.dto.VehicleDto;
import com.zxq.iov.cloud.sp.vp.common.ExceptionConstants;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 安防 基础服务类
 *
 * @author 叶荣杰
 * create date 2015-7-24 9:57
 * modify date 2015-8-18 14:57
 * @version 0.2, 2015-8-18
 */
public class BaseService {

    @Autowired
    private ITboxApi tboxApi;
    @Autowired
    private IVehicleApi vehicleApi;

    /**
     * 验证必填的输入参数
     * @param argNames              参数名称
     * @param args                  参数变量
     * @throws ServLayerException   业务异常
     */
    protected void AssertRequired(String argNames, Object... args) throws ServLayerException {
        boolean isNull = false;
        String[] _argNames = argNames.split(",");
        StringBuffer argName = new StringBuffer("");
        int index = 0;
        for(Object arg : args) {
            if(null == arg) {
                argName.append(_argNames[index]);
                isNull = true;
            }
            index++;
        }
        if(isNull) {
            throw new ServLayerException(ExceptionConstants.PARAMS_IS_NULL,
                    argName.toString() + " are not allowed to be null");
        }
    }

    /**
     * 根据ID得到TBOX传输对象
     * @param tboxId                TBOX ID
     * @return                      TBOX传输对象
     */
    protected TboxDto findTboxById(Long tboxId) {
        TboxDto tboxDto = new TboxDto();
        tboxDto.setId(tboxId);
        List<TboxDto> list = tboxApi.findTbox(tboxDto);
        return list.size()>0?list.get(0):null;
    }

    /**
     * 根据ID得到车辆唯一码
     * @param tboxId                TBOX ID
     * @return                      车辆唯一码
     */
    protected String findVinById(Long tboxId) {
        TboxDto tboxDto = findTboxById(tboxId);
        return null!=tboxDto?tboxDto.getVin():null;
    }

    /**
     * 根据ID得到用户ID
     * @param tboxId                TBOX ID
     * @return                      用户ID
     */
    protected Long findUserIdById(Long tboxId) {
        String vin = findVinById(tboxId);
        VehicleDto vehicleDto = null;
        if(null != vin) {
            vehicleDto = vehicleApi.findVehicleByVin(vin);
        }
        return null!=vehicleDto?vehicleDto.getUserId():null;
    }

    /**
     * 根据车辆唯一码得到TBOX ID
     * @param vin                   车辆唯一码
     * @return                      TBOX ID
     */
    protected Long findTboxIdByVin(String vin) {
        TboxDto tboxDto = new TboxDto();
        tboxDto.setVin(vin);
        List<TboxDto> list = tboxApi.findTbox(tboxDto);
        return list.size()>0?list.get(0).getId():null;
    }

    /**
     * 更新TBOX
     * @param tboxDto
     */
    protected void updateTbox(TboxDto tboxDto) {
        tboxApi.updateTboxInfo(tboxDto);
    }
}
