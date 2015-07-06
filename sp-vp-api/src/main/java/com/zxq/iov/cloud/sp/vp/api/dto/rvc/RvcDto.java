package com.zxq.iov.cloud.sp.vp.api.dto.rvc;


/**
 * 安防 远程控制命令传输对象
 *
 * @author 叶荣杰
 * create date 2015-7-6 16:43
 * modify date
 * @version 0.1, 2015-7-6
 */
public class RvcDto {

    private String command;
    private String parameter;

    public RvcDto() {}

    public RvcDto(String command, String parameter) {
        this.command = command;
        this.parameter = parameter;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }
}
