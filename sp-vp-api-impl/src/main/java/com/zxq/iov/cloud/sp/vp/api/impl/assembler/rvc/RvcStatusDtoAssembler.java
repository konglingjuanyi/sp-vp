package com.zxq.iov.cloud.sp.vp.api.impl.assembler.rvc;

import com.zxq.iov.cloud.sp.vp.api.dto.rvc.RvcStatusDto;
import com.zxq.iov.cloud.sp.vp.entity.rvc.ControlCommand;

/**
 * 安防 控制命令状态传输对象装配类
 *
 * @author 叶荣杰
 * create date 2015-7-30 9:56
 * modify date
 * @version 0.1, 2015-7-30
 */
public class RvcStatusDtoAssembler {

    public RvcStatusDto toDto(final ControlCommand controlCommand) {
        return new RvcStatusDto(controlCommand.getCommandStatus(), controlCommand.getFailureType());
    }

}
