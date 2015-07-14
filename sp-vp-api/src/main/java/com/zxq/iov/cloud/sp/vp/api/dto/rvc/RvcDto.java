package com.zxq.iov.cloud.sp.vp.api.dto.rvc;


import java.util.List;
import java.util.Map;

/**
 * 安防 远程控制命令传输对象
 *
 * @author 叶荣杰
 * create date 2015-7-6 16:43
 * modify date 2015-7-14 14:20
 * @version 0.2, 2015-7-14
 */
public class RvcDto {

    private String command;
    private List<Map<String, Object>> parameters;

    public RvcDto() {}

    public RvcDto(String command, List<Map<String, Object>> parameters) {
        this.command = command;
        this.parameters = parameters;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public List<Map<String, Object>> getParameters() {
        return parameters;
    }

    public void setParameters(List<Map<String, Object>> parameters) {
        this.parameters = parameters;
    }
}
