package com.zxq.iov.cloud.sp.vp.api.dto.rvc;


import java.util.Map;

/**
 * 安防 远程控制命令传输对象
 *
 * @author 叶荣杰
 * create date 2015-7-6 16:43
 * modify date 2015-7-21 16:04
 * @version 0.4, 2015-7-21
 */
public class RvcDto {

    private byte[] command;
    private Map<String, Object> parameters;

    public RvcDto() {}

    public RvcDto(byte[] command, Map<String, Object> parameters) {
        this.command = command;
        this.parameters = parameters;
    }

    public byte[] getCommand() {
        return command;
    }

    public void setCommand(byte[] command) {
        this.command = command;
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
    }
}
