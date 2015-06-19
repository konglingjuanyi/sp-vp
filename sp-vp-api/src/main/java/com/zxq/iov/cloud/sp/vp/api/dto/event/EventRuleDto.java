package com.zxq.iov.cloud.sp.vp.api.dto.event;

/**
 * 安防 事件规则DTO
 *
 * @author 叶荣杰
 * create date 2015-6-18 10:45
 * modify date
 * @version 0.1, 2015-6-18
 */
public class EventRuleDto {

    private String name;
    private String operator;
    private String value;

    public EventRuleDto() {
    }

    public EventRuleDto(String name, String operator, String value) {
        this.name = name;
        this.operator = operator;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
