package com.zxq.iov.cloud.sp.vp.api.dto.rvc;


import java.io.Serializable;

/**
 * 安防 远程控制命令状态传输对象
 *
 * @author 叶荣杰
 * create date 2015-7-29 18:14
 * modify date 2015-7-30 9:59
 * @version 0.2, 2015-7-30
 */
public class RvcStatusDto implements Serializable {

    private String commandStatus;
    private Integer failureType;

    public RvcStatusDto() {
    }

    public RvcStatusDto(String commandStatus, Integer failureType) {
        this.commandStatus = commandStatus;
        this.failureType = failureType;
    }

    public String getCommandStatus() {
        return commandStatus;
    }

    public void setCommandStatus(String commandStatus) {
        this.commandStatus = commandStatus;
    }

    public Integer getFailureType() {
        return failureType;
    }

    public void setFailureType(Integer failureType) {
        this.failureType = failureType;
    }
}
