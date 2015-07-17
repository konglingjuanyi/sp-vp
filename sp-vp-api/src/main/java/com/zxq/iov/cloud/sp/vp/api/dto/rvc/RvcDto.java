package com.zxq.iov.cloud.sp.vp.api.dto.rvc;


import java.util.List;
import java.util.Map;

/**
 * 安防 远程控制命令传输对象
 *
 * @author 叶荣杰
 * create date 2015-7-6 16:43
 * modify date 2015-7-17 17:49
 * @version 0.3, 2015-7-17
 */
public class RvcDto {

    private byte[] command;
    private List<Map<String, Object>> parameters;

    public RvcDto() {}

    public RvcDto(byte[] command, List<Map<String, Object>> parameters) {
        this.command = command;
        this.parameters = parameters;
    }

    public byte[] getCommand() {
        return command;
    }

    public void setCommand(byte[] command) {
        this.command = command;
    }

    public List<Map<String, Object>> getParameters() {
        return parameters;
    }

    public void setParameters(List<Map<String, Object>> parameters) {
        this.parameters = parameters;
    }
}
